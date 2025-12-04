package me.synology.freash97.comment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.synology.freash97.board.domain.BoardDTO;
import me.synology.freash97.board.service.BoardService;
import me.synology.freash97.comment.domain.CommentDTO;
import me.synology.freash97.comment.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * packageName   : me.synology.freash97.comment.controller
 * fileName      : CommentController
 * author        : iptab
 * date          : 2025-04-28
 * time          : 오후 10:47
 * description   :
 * ====================================================
 * DATE                  AUTHOR              NOTE
 * ----------------------------------------------------
 * 2025-04-28               iptab             최초 생성
 */

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("comment/")
public class CommentController {
    private final CommentService commentService;
    private final BoardService boardService;

    @PostMapping("/boardComment")
    public String addComment(@ModelAttribute CommentDTO commentDTO, Model model) throws Exception{
        commentService.save(commentDTO, commentDTO.getUsername());

        //상세 이력
        BoardDTO boardDetail = boardService.findById(commentDTO.getBoardSq());
        List<CommentDTO> commentDTOList = commentService.findByBoardSq(boardDetail.getBoardSq());

        model.addAttribute("boardDetail", boardDetail);
        model.addAttribute("commentDTOList", commentDTOList);

        return "redirect:/board/boardDetail?boardSq=" + commentDTO.getBoardSq();
    }

    @PostMapping("/{commentId}/delete")
    public String deleteComment(@PathVariable Long commentId) throws Exception {
        commentService.delete(commentId);
        return "redirect:/board/{boardId}";
    }
}
