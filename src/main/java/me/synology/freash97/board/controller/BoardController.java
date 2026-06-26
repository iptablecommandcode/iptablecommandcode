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

    private void addCommonModel(Model model) throws Exception {
        model.addAttribute("categories", categoryService.findAllActive());
        model.addAttribute("allTags",    tagService.findAllActive());
    }

    @GetMapping("/boardList")
    public String boardList(@RequestParam(required=false) Integer categorySq,
                            @RequestParam(required=false) Integer tagSq,
                            Model model) throws Exception {
        addCommonModel(model);
        List<BoardDTO> boards;
        if      (tagSq      != null) boards = boardService.findByTag(tagSq);
        else if (categorySq != null) boards = boardService.findByCategory(categorySq);
        else                         boards = boardService.findAll();
        model.addAttribute("boards",     boards);
        model.addAttribute("categorySq", categorySq);
        model.addAttribute("tagSq",      tagSq);
        return "board/boardList";
    }

    @GetMapping("/boardDetail")
    public String boardDetail(@RequestParam Integer boardSq, Model model) throws Exception {
        addCommonModel(model);
        BoardDTO         board    = boardService.findById(boardSq);
        List<CommentDTO> comments = commentService.findByBoardSq(boardSq);
        model.addAttribute("boardDetail",    board);
        model.addAttribute("commentDTOList", comments);
        return "board/boardDetail";
    }

    @GetMapping("/boardWrite")
    public String createForm(HttpSession session, Model model) throws Exception {
        if (session.getAttribute("loginUser") == null) return "redirect:/sign/signIn";
        addCommonModel(model);
        return "board/boardWrite";
    }

    @PostMapping("/boardWrite")
    public String createBoard(@ModelAttribute BoardDTO board, HttpSession session) throws Exception {
        SignDTO signDTO = (SignDTO) session.getAttribute("loginUser");
        if (signDTO == null) return "redirect:/sign/signIn";
        boardService.save(board, signDTO.getUsername());
        return "redirect:/board/boardList";
    }

    @GetMapping("/boardEdit")
    public String editForm(@RequestParam Integer boardSq, Model model, HttpSession session) throws Exception {
        SignDTO signDTO = (SignDTO) session.getAttribute("loginUser");
        if (signDTO == null) return "redirect:/sign/signIn";
        BoardDTO board = boardService.findById(boardSq);
        if (signDTO.getUserId().equals(board.getUserId())) {
            addCommonModel(model);
            // 기존 태그 → tagNames 문자열로 변환
            List<TagDTO> tags = tagService.findByBoardSq(boardSq);
            String tagNames = tags.stream().map(TagDTO::getTagName).reduce((a,b)->a+","+b).orElse("");
            board.setTagNames(tagNames);
            model.addAttribute("board", board);
            return "board/boardEdit";
        }
        model.addAttribute("boardDetail",    board);
        model.addAttribute("commentDTOList", commentService.findByBoardSq(boardSq));
        model.addAttribute("regMsg", "작성자만 수정할 수 있습니다.");
        return "board/boardDetail";
    }

    @PostMapping("/boardUpdate")
    public String updateBoard(@ModelAttribute BoardDTO board, HttpSession session) throws Exception {
        SignDTO signDTO = (SignDTO) session.getAttribute("loginUser");
        if (signDTO == null) return "redirect:/sign/signIn";
        boardService.update(board, signDTO.getUsername());
        return "redirect:/board/boardDetail?boardSq=" + board.getBoardSq();
    }

    @PostMapping("/boardDelete")
    public String deleteBoard(@RequestParam int boardSq, HttpSession session, Model model) throws Exception {
        SignDTO signDTO = (SignDTO) session.getAttribute("loginUser");
        if (signDTO == null) return "redirect:/sign/signIn";
        BoardDTO board = boardService.findById(boardSq);
        if (signDTO.getUserId().equals(board.getUserId())) {
            boardService.delete(boardSq);
            return "redirect:/board/boardList";
        }
        model.addAttribute("boardDetail",    board);
        model.addAttribute("commentDTOList", commentService.findByBoardSq(boardSq));
        model.addAttribute("regMsg", "작성자만 삭제할 수 있습니다.");
        return "board/boardDetail";
    }
}
