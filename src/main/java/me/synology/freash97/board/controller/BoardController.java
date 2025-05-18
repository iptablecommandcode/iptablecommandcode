package me.synology.freash97.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.synology.freash97.board.service.BoardService;
import me.synology.freash97.board.domain.BoardDTO;
import me.synology.freash97.comment.domain.CommentDTO;
import me.synology.freash97.comment.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    public String createBoard(@ModelAttribute BoardDTO board, Principal principal) throws Exception {
        boardService.save(board, principal.getName());
        return "redirect:/board/boardWrite";
    }

    @GetMapping("/boardEdit")
    public String editForm(@RequestParam Integer boardSq, Model model) throws Exception {
        BoardDTO board = boardService.findById(boardSq);
        model.addAttribute("board", board);
        return "/board/boardEdit";  // board-edit.html
    }

    //삭제처리
    @PostMapping("/boardDelete")
    public String deleteBoard(@RequestParam("boardSq") int boardSq) throws Exception {
        boardService.delete(boardSq);
        return "redirect:/board/boardList";
    }
}
