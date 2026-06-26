package me.synology.freash97.board.service;

import me.synology.freash97.board.domain.BoardDTO;

import java.util.List;

public interface BoardService {
    List<BoardDTO> findAll();
    List<BoardDTO> findByCategory(int categorySq);
    List<BoardDTO> findByTag(int tagSq);
    BoardDTO findById(int boardSq);
    void increaseViewCount(int boardSq);
    void save(BoardDTO boardDTO, String username);
    void update(BoardDTO boardDTO, String username);
    void delete(int boardSq);
    void updateNotice(BoardDTO boardDTO);
    List<BoardDTO> findAllForAdmin();
    int countAll();
}
