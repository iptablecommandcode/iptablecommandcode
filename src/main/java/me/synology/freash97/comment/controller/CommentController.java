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

import java.util.List;

/**
 * packageName   : me.synology.freash97.comment.controller
 * fileName      : CommentController
 * author        : iptab
 * date          : 2025-04-28
 * ====================================================
 * DATE                  AUTHOR              NOTE
 * ----------------------------------------------------
 * 2025-04-28            iptab               최초 생성
 * 2025-12-07            iptab               버그 수정
 *   - deleteComment 리다이렉트 URL 오류 수정 ({boardId} 미바인딩 → boardSq 파라미터 수신)
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("comment/")
public class CommentController {

    private final CommentService commentService;
    private final BoardService boardService;

    @PostMapping("/boardComment")
    public String addComment(@ModelAttribute CommentDTO commentDTO, Model model) throws Exception {
        commentService.save(commentDTO, commentDTO.getUsername());
        return "redirect:/board/boardDetail?boardSq=" + commentDTO.getBoardSq();
    }

    // [수정] boardSq 파라미터 추가, 리다이렉트 URL 정상화
    @PostMapping("/{commentId}/delete")
    public String deleteComment(@PathVariable Long commentId,
                                @RequestParam Integer boardSq) throws Exception {
        commentService.delete(commentId);
        return "redirect:/board/boardDetail?boardSq=" + boardSq;
    }
}
