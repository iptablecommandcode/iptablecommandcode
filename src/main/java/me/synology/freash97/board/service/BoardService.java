package me.synology.freash97.board.service;

import me.synology.freash97.board.domain.BoardDTO;

import java.util.List;

/**
 * packageName   : me.synology.freash97.board.service
 * fileName      : boardService
 * author        : iptable
 * date          : 2025-04-25
 * time          : 오후 11:46
 * description   :
 * ====================================================
 * DATE                  AUTHOR              NOTE
 * ----------------------------------------------------
 * 2025-04-25               iptab             최초 생성
 */
public interface BoardService {
    List<BoardDTO> findAll() throws Exception;

    BoardDTO findById(Integer boardSq) throws Exception;

    void save(BoardDTO boardDTO, String username) throws Exception;

    void update(BoardDTO boardDTO) throws Exception;

    void delete(Integer boardSq) throws Exception;
}
