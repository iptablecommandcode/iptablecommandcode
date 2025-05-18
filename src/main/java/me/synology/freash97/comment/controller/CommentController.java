package me.synology.freash97.comment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.synology.freash97.comment.domain.CommentDTO;
import me.synology.freash97.comment.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public String addComment(@ModelAttribute CommentDTO commentDTO) throws Exception{
        commentService.save(commentDTO, commentDTO.getUsername());
        return "redirect:/board/{boardId}";
    }

    @PostMapping("/{commentId}/delete")
    public String deleteComment(@PathVariable Long commentId) throws Exception {
        commentService.delete(commentId);
        return "redirect:/board/{boardId}";
    }
}
