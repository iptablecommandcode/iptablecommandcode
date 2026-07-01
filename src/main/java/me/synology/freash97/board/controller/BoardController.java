package me.synology.freash97.board.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.synology.freash97.board.domain.BoardDTO;
import me.synology.freash97.board.service.BoardService;
import me.synology.freash97.category.domain.CategoryDTO;
import me.synology.freash97.category.service.CategoryService;
import me.synology.freash97.comment.domain.CommentDTO;
import me.synology.freash97.comment.service.CommentService;
import me.synology.freash97.sign.domain.SignDTO;
import me.synology.freash97.tag.domain.TagDTO;
import me.synology.freash97.tag.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService    boardService;
    private final CommentService  commentService;
    private final CategoryService categoryService;
    private final TagService      tagService;

    //url 상수 지정
    private static final String BOARD_DETAIL_URL = "board/boardDetail";
    private static final String REDIRECT_BOARD_LIST_URL = "redirect:/board/boardList";
    private static final String REDIRECT_SIGN_IN_URL = "redirect:/sign/signIn";
    private static final String REDIRECT_MYCATAGORIS_URL = "redirect:/board/myCategories";

    //value 상수 지정
    private static final String BOARD_DETAIL = "boardDetail";
    private static final String COMMENT_DTO_LIST = "commentDTOList";

    private SignDTO getLoginUser(HttpSession session) {
        return (SignDTO) session.getAttribute("loginUser");
    }

    private boolean isAdmin(SignDTO signDTO) {
        return signDTO != null && "Y".equals(signDTO.getAdmin());
    }

    private Integer getUserId(SignDTO signDTO) {
        return signDTO != null ? signDTO.getUserId() : null;
    }

    private void addCommonModel(Model model, HttpSession session) throws Exception {
        SignDTO loginUser = getLoginUser(session);
        model.addAttribute("categories", categoryService.findVisible(getUserId(loginUser), isAdmin(loginUser)));
        model.addAttribute("allTags",    tagService.findAllActive());
    }

    @GetMapping("/boardList")
    public String boardList(@RequestParam(required=false) Integer categorySq,
                            @RequestParam(required=false) Integer tagSq,
                            HttpSession session,
                            Model model) throws Exception {
        addCommonModel(model, session);
        SignDTO loginUser = getLoginUser(session);
        Integer userId = getUserId(loginUser);
        boolean admin = isAdmin(loginUser);
        List<BoardDTO> boards;
        if      (tagSq      != null) boards = boardService.findVisibleByTag(tagSq, userId, admin);
        else if (categorySq != null) boards = boardService.findVisibleByCategory(categorySq, userId, admin);
        else                         boards = boardService.findVisible(userId, admin);
        model.addAttribute("boards",     boards);
        model.addAttribute("categorySq", categorySq);
        model.addAttribute("tagSq",      tagSq);
        return "board/boardList";
    }

    @GetMapping("/boardDetail")
    public String boardDetail(@RequestParam Integer boardSq, Model model, HttpSession session) throws Exception {
        addCommonModel(model, session);
        SignDTO loginUser = getLoginUser(session);
        BoardDTO board = boardService.findVisibleById(boardSq, getUserId(loginUser), isAdmin(loginUser));
        if (board == null) {
            return REDIRECT_BOARD_LIST_URL;
        }
        board.setTags(tagService.findByBoardSq(boardSq));
        List<CommentDTO> comments = commentService.findByBoardSq(boardSq);
        model.addAttribute(BOARD_DETAIL,    board);
        model.addAttribute(COMMENT_DTO_LIST, comments);
        return BOARD_DETAIL_URL;
    }

    @GetMapping("/boardWrite")
    public String createForm(HttpSession session, Model model) throws Exception {
        if (session.getAttribute("loginUser") == null) return REDIRECT_SIGN_IN_URL;
        addCommonModel(model, session);
        return "board/boardWrite";
    }

    @PostMapping("/boardWrite")
    public String createBoard(@ModelAttribute BoardDTO board, HttpSession session) throws Exception {
        SignDTO signDTO = getLoginUser(session);
        if (signDTO == null) return REDIRECT_SIGN_IN_URL;
        if (!categoryService.canUseCategory(board.getCategorySq(), signDTO.getUserId(), isAdmin(signDTO))) {
            return "redirect:/board/boardWrite?categoryError=true";
        }
        board.setUserId(signDTO.getUserId());
        board.setUsername(signDTO.getUsername());
        boardService.save(board, signDTO.getUsername());
        return REDIRECT_BOARD_LIST_URL;
    }

    @GetMapping("/boardEdit")
    public String editForm(@RequestParam Integer boardSq, Model model, HttpSession session) throws Exception {
        SignDTO signDTO = getLoginUser(session);
        if (signDTO == null) return REDIRECT_SIGN_IN_URL;
        BoardDTO board = boardService.findById(boardSq);
        if (board == null) {
            return REDIRECT_BOARD_LIST_URL;
        }
        if (signDTO.getUserId().equals(board.getUserId())) {
            addCommonModel(model, session);
            // 기존 태그 → tagNames 문자열로 변환
            List<TagDTO> tags = tagService.findByBoardSq(boardSq);
            String tagNames = tags.stream().map(TagDTO::getTagName).reduce((a,b)->a+","+b).orElse("");
            board.setTagNames(tagNames);
            model.addAttribute("board", board);
            return "board/boardEdit";
        }
        board = boardService.findVisibleById(boardSq, signDTO.getUserId(), isAdmin(signDTO));
        if (board == null) {
            return REDIRECT_BOARD_LIST_URL;
        }
        addCommonModel(model, session);
        model.addAttribute(BOARD_DETAIL,    board);
        model.addAttribute(COMMENT_DTO_LIST, commentService.findByBoardSq(boardSq));
        model.addAttribute("regMsg", "작성자만 수정할 수 있습니다.");
        return BOARD_DETAIL_URL;
    }

    @PostMapping("/boardUpdate")
    public String updateBoard(@ModelAttribute BoardDTO board, HttpSession session) throws Exception {
        SignDTO signDTO = getLoginUser(session);
        if (signDTO == null) return REDIRECT_SIGN_IN_URL;
        BoardDTO savedBoard = boardService.findById(board.getBoardSq());
        if (savedBoard == null) {
            return REDIRECT_BOARD_LIST_URL;
        }
        if (!signDTO.getUserId().equals(savedBoard.getUserId())) {
            return "redirect:/board/boardDetail?boardSq=" + board.getBoardSq();
        }
        if (!categoryService.canUseCategory(board.getCategorySq(), signDTO.getUserId(), isAdmin(signDTO))) {
            return "redirect:/board/boardEdit?boardSq=" + board.getBoardSq() + "&categoryError=true";
        }
        boardService.update(board, signDTO.getUsername());
        return "redirect:/board/boardDetail?boardSq=" + board.getBoardSq();
    }

    @PostMapping("/boardDelete")
    public String deleteBoard(@RequestParam int boardSq, HttpSession session, Model model) throws Exception {
        SignDTO signDTO = getLoginUser(session);
        if (signDTO == null) return REDIRECT_SIGN_IN_URL;
        BoardDTO board = boardService.findById(boardSq);
        if (board == null) {
            return REDIRECT_BOARD_LIST_URL;
        }
        if (signDTO.getUserId().equals(board.getUserId())) {
            boardService.delete(boardSq);
            return REDIRECT_BOARD_LIST_URL;
        }
        board = boardService.findVisibleById(boardSq, signDTO.getUserId(), isAdmin(signDTO));
        if (board == null) {
            return REDIRECT_BOARD_LIST_URL;
        }
        addCommonModel(model, session);
        model.addAttribute(BOARD_DETAIL,    board);
        model.addAttribute(COMMENT_DTO_LIST, commentService.findByBoardSq(boardSq));
        model.addAttribute("regMsg", "작성자만 삭제할 수 있습니다.");
        return BOARD_DETAIL_URL;
    }

    @GetMapping("/myCategories")
    public String myCategories(HttpSession session, Model model) throws Exception {
        SignDTO signDTO = getLoginUser(session);
        if (signDTO == null) return REDIRECT_SIGN_IN_URL;
        addCommonModel(model, session);
        model.addAttribute("myCategories", categoryService.findByOwner(signDTO.getUserId()));
        model.addAttribute("commonCategories", categoryService.findCommonActive());
        return "board/myCategoryList";
    }

    @PostMapping("/categories/save")
    public String saveMyCategory(@ModelAttribute CategoryDTO category, HttpSession session) throws Exception {
        SignDTO signDTO = getLoginUser(session);
        if (signDTO == null) return REDIRECT_SIGN_IN_URL;
        category.setUserId(signDTO.getUserId());
        categoryService.save(category, signDTO.getUsername());
        return REDIRECT_MYCATAGORIS_URL;
    }

    @PostMapping("/categories/update")
    public String updateMyCategory(@ModelAttribute CategoryDTO category, HttpSession session) throws Exception {
        SignDTO signDTO = getLoginUser(session);
        if (signDTO == null) return REDIRECT_SIGN_IN_URL;
        if (!categoryService.canManageCategory(category.getCategorySq(), signDTO.getUserId(), isAdmin(signDTO))) {
            return REDIRECT_MYCATAGORIS_URL;
        }
        category.setUserId(signDTO.getUserId());
        categoryService.update(category, signDTO.getUsername());
        return REDIRECT_MYCATAGORIS_URL;
    }

    @PostMapping("/categories/delete")
    public String deleteMyCategory(@RequestParam int categorySq, HttpSession session) throws Exception {
        SignDTO signDTO = getLoginUser(session);
        if (signDTO == null) return REDIRECT_SIGN_IN_URL;
        if (categoryService.canManageCategory(categorySq, signDTO.getUserId(), isAdmin(signDTO))) {
            categoryService.delete(categorySq);
        }
        return REDIRECT_MYCATAGORIS_URL;
    }
}
