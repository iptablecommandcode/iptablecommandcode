package me.synology.freash97.comment.service.impl;

import me.synology.freash97.comment.domain.CommentDTO;
import me.synology.freash97.comment.mapper.CommentMapper;
import me.synology.freash97.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<CommentDTO> findByBoardSq(int boardSq) {
        return commentMapper.findByBoardSq(boardSq);
    }

    @Override
    public List<CommentDTO> findReplies(int parentCommentSq) {
        return commentMapper.findReplies(parentCommentSq);
    }

    @Override
    public void save(CommentDTO commentDTO, String username) {
        commentDTO.setCreateUser(username);
        commentMapper.save(commentDTO);
    }

    @Override
    public void delete(long commentSq) {
        commentMapper.delete(commentSq);
    }

    @Override
    public List<CommentDTO> findAllForAdmin() {
        return commentMapper.findAllForAdmin();
    }

    @Override
    public int countAll() {
        return commentMapper.countAll();
    }
}
