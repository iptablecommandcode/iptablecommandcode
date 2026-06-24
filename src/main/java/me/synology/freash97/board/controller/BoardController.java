package me.synology.freash97.board.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.synology.freash97.board.service.BoardService;
import me.synology.freash97.board.domain.BoardDTO;
import me.synology.freash97.comment.domain.CommentDTO;
import me.synology.freash97.comment.service.CommentService;
import me.synology.freash97.sign.domain.SignDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName   : me.synology.freash97.board.controller
 * fileName      : BoardController
 * author        : iptable
 * date          : 2025-04-23
 * ====================================================
 * DATE                  AUTHOR              NOTE
 * ----------------------------------------------------
 * 2025-04-23            iptab               최초 생성
 * 2025-12-07            iptab               버그 수정
 *   - Integer 비교 == → equals() 변경
 *   - 세션 null 체크 추가 (미로그인 시 로그인 페이지 리다이렉트)
 */
@Slf4j
@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;

    @GetMapping("/boardList")
    public String boardList(Model model) throws Exception {
        List<BoardDTO> boards = boardService.findAll();
        model.addAttribute("boards", boards);
        return "board/boardList";
    }

    @GetMapping("/boardDetail")
    public String boardDetail(@RequestParam Integer boardSq, Model model) throws Exception {
        BoardDTO boardDetail = boardService.findById(boardSq);
        List<CommentDTO> commentDTOList = commentService.findByBoardSq(boardDetail.getBoardSq());

        model.addAttribute("boardDetail", boardDetail);
        model.addAttribute("commentDTOList", commentDTOList);
        return "board/boardDetail";
    }

    @GetMapping("/boardWrite")
    public String createForm(HttpSession session) {
        // [수정] 세션 null 체크 - 미로그인 시 로그인 페이지로
        if (session.getAttribute("loginUser") == null) {
            return "redirect:/sign/signIn";
        }
        return "board/boardWrite";
    }

    @PostMapping("/boardWrite")
    public String createBoard(@ModelAttribute BoardDTO board, HttpSession session) throws Exception {
        // [수정] 세션 null 체크
        SignDTO signDTO = (SignDTO) session.getAttribute("loginUser");
        if (signDTO == null) {
            return "redirect:/sign/signIn";
        }

        boardService.save(board, signDTO.getUsername());
        return "redirect:/board/boardList";
    }

    @GetMapping("/boardEdit")
    public String editForm(@RequestParam Integer boardSq, Model model, HttpSession session) throws Exception {
        // [수정] 세션 null 체크
        SignDTO signDTO = (SignDTO) session.getAttribute("loginUser");
        if (signDTO == null) {
            return "redirect:/sign/signIn";
        }

        BoardDTO board = boardService.findById(boardSq);

        // [수정] Integer 비교 == → equals()
        if (signDTO.getUserId().equals(board.getUserId())) {
            model.addAttribute("board", board);
            return "board/boardEdit";
        } else {
            List<CommentDTO> commentDTOList = commentService.findByBoardSq(board.getBoardSq());
            model.addAttribute("boardDetail", board);
            model.addAttribute("commentDTOList", commentDTOList);
            model.addAttribute("regMsg", "작성자가 아닐경우 글을 수정할 수 없습니다.");
            return "board/boardDetail";
        }
    }

    @PostMapping("/boardUpdate")
    public String updateBoard(@ModelAttribute BoardDTO board, HttpSession session) throws Exception {
        // [수정] 세션 null 체크
        SignDTO signDTO = (SignDTO) session.getAttribute("loginUser");
        if (signDTO == null) {
            return "redirect:/sign/signIn";
        }

        boardService.update(board);
        return "redirect:/board/boardList";
    }

    @PostMapping("/boardDelete")
    public String deleteBoard(@RequestParam("boardSq") int boardSq, HttpSession session, Model model) throws Exception {
        // [수정] 세션 null 체크
        SignDTO signDTO = (SignDTO) session.getAttribute("loginUser");
        if (signDTO == null) {
            return "redirect:/sign/signIn";
        }

        BoardDTO board = boardService.findById(boardSq);

        // [수정] Integer 비교 == → equals()
        if (signDTO.getUserId().equals(board.getUserId())) {
            boardService.delete(boardSq);
            return "redirect:/board/boardList";
        } else {
            List<CommentDTO> commentDTOList = commentService.findByBoardSq(board.getBoardSq());
            model.addAttribute("boardDetail", board);
            model.addAttribute("commentDTOList", commentDTOList);
            model.addAttribute("regMsg", "작성자가 아닐경우 글을 삭제할 수 없습니다.");
            return "board/boardDetail";
        }
    }
}
