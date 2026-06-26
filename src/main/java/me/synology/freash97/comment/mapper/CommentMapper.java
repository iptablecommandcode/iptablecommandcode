package me.synology.freash97.comment.mapper;

import me.synology.freash97.comment.domain.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<CommentDTO> findByBoardSq(int boardSq);
    List<CommentDTO> findReplies(int parentCommentSq);
    void save(CommentDTO commentDTO);
    void delete(long commentSq);
    List<CommentDTO> findAllForAdmin();
    int countAll();
}
