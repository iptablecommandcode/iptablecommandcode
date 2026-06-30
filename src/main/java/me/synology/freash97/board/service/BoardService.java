package me.synology.freash97.board.service;

import me.synology.freash97.board.domain.BoardDTO;

import java.util.List;

public interface BoardService {
    List<BoardDTO> findAll();
    List<BoardDTO> findVisible(Integer userId, boolean admin);
    List<BoardDTO> findByCategory(int categorySq);
    List<BoardDTO> findVisibleByCategory(int categorySq, Integer userId, boolean admin);
    List<BoardDTO> findByTag(int tagSq);
    List<BoardDTO> findVisibleByTag(int tagSq, Integer userId, boolean admin);
    BoardDTO findById(int boardSq);
    BoardDTO findVisibleById(int boardSq, Integer userId, boolean admin);
    void increaseViewCount(int boardSq);
    void save(BoardDTO boardDTO, String username);
    void update(BoardDTO boardDTO, String username);
    void delete(int boardSq);
    void updateNotice(BoardDTO boardDTO);
    List<BoardDTO> findAllForAdmin();
    int countAll();
}
