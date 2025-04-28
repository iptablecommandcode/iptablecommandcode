package me.synology.freash97.comment.service;

import me.synology.freash97.comment.domain.CommentDTO;

import java.util.List;

/**
 * packageName   : me.synology.freash97.comment.service
 * fileName      : CommentService
 * author        : iptab
 * date          : 2025-04-28
 * time          : 오후 11:10
 * description   :
 * ====================================================
 * DATE                  AUTHOR              NOTE
 * ----------------------------------------------------
 * 2025-04-28               iptab             최초 생성
 */
public interface CommentService {
    List<CommentDTO> findByBoardSq(Long boardSq) throws Exception;

    void save(CommentDTO commentDTO, String username) throws Exception;

    void delete(Long commentSq) throws Exception;
}
