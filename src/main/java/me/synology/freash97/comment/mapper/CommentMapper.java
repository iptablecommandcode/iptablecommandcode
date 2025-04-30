package me.synology.freash97.comment.mapper;

import me.synology.freash97.comment.domain.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * packageName   : me.synology.freash97.comment.mapper
 * fileName      : CommentMapper
 * author        : iptab
 * date          : 2025-04-28
 * time          : 오후 10:51
 * description   :
 * ====================================================
 * DATE                  AUTHOR              NOTE
 * ----------------------------------------------------
 * 2025-04-28               iptab             최초 생성
 */
@Mapper
public interface CommentMapper {

    List<CommentDTO> findByBoardSq(Integer boardSq);

    void save(CommentDTO commentDTO);

    void delete(Long commentSq);
}
