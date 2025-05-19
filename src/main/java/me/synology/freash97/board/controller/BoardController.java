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
 * fileName      : boardController
 * author        : iptable
 * date          : 2025-04-23
 * time          : 오후 10:38
 * description   :
 * ====================================================
 * DATE                  AUTHOR              NOTE
 * ----------------------------------------------------
 * 2025-04-23               iptab             최초 생성
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
        return "/board/boardList";  // board-list.html
    }

    @GetMapping("/boardDetail")
    public String boardDetail(@RequestParam Integer boardSq, Model model) throws Exception {
        //상세 이력
        BoardDTO boardDetail = boardService.findById(boardSq);
        List<CommentDTO> commentDTOList = commentService.findByBoardSq(boardDetail.getBoardSq());

        model.addAttribute("boardDetail", boardDetail);
        model.addAttribute("commentDTOList", commentDTOList);
        return "board/boardDetail";  // board-detail.html
    }

    @GetMapping("/boardWrite")
    public String createForm() {
        return "/board/boardWrite";  // board-new.html
    }

    @PostMapping("/boardWrite")
    public String createBoard(@ModelAttribute BoardDTO board, HttpSession session) throws Exception {
        SignDTO signDTO = (SignDTO) session.getAttribute("loginUser");

        boardService.save(board, signDTO.getUsername());
        return "redirect:/board/boardList";
    }

    @GetMapping("/boardEdit")
    public String editForm(@RequestParam Integer boardSq, Model model, HttpSession session) throws Exception {

        SignDTO signDTO = (SignDTO) session.getAttribute("loginUser");
        BoardDTO board = boardService.findById(boardSq);

        //세션 확인 후 수정 가능하도록 처리
        if (signDTO.getUserId() == board.getUserId()) {
            model.addAttribute("board", board);
            return "/board/boardEdit";  // board-edit.html
        } else {
            //로그인 없을경우 Detail 페이지 이동
            BoardDTO boardDetail = boardService.findById(boardSq);
            List<CommentDTO> commentDTOList = commentService.findByBoardSq(boardDetail.getBoardSq());

            model.addAttribute("boardDetail", boardDetail);
            model.addAttribute("commentDTOList", commentDTOList);
            return "/board/boardDetail";  // board-list.html
        }
    }

    @PostMapping("/boardUpdate")
    public String updateBoard(@ModelAttribute BoardDTO board, HttpSession session) throws Exception {
        //SignDTO signDTO = (SignDTO) session.getAttribute("loginUser");

        boardService.update(board);
        return "redirect:/board/boardList";
    }

    //삭제처리
    @PostMapping("/boardDelete")
    public String deleteBoard(@RequestParam("boardSq") int boardSq, HttpSession session, Model model) throws Exception {

        SignDTO signDTO = (SignDTO) session.getAttribute("loginUser");
        BoardDTO board = boardService.findById(boardSq);

        //세션 확인 후 수정 가능하도록 처리
        if (signDTO.getUserId() == board.getUserId()) {
            boardService.delete(boardSq);
            return "redirect:/board/boardList";
        } else {
            //상세 이력
            BoardDTO boardDetail = boardService.findById(boardSq);
            List<CommentDTO> commentDTOList = commentService.findByBoardSq(boardDetail.getBoardSq());

            model.addAttribute("boardDetail", boardDetail);
            model.addAttribute("commentDTOList", commentDTOList);
            return "board/boardDetail";  // board-detail.html
        }
    }
}
