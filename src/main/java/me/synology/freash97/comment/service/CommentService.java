package me.synology.freash97.comment.service;

import me.synology.freash97.comment.domain.CommentDTO;

import java.util.List;

public interface CommentService {
    List<CommentDTO> findByBoardSq(int boardSq);
    List<CommentDTO> findReplies(int parentCommentSq);
    void save(CommentDTO commentDTO, String username);
    void delete(long commentSq);
    List<CommentDTO> findAllForAdmin();
    int countAll();
}
