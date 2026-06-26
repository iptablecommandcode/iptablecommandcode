package me.synology.freash97.board.mapper;

import me.synology.freash97.board.domain.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDTO> findAll();
    List<BoardDTO> findByCategory(int categorySq);
    List<BoardDTO> findByTag(int tagSq);
    BoardDTO findById(int boardSq);
    void increaseViewCount(int boardSq);
    void save(BoardDTO boardDTO);
    void update(BoardDTO boardDTO);
    void delete(int boardSq);
    void updateNotice(BoardDTO boardDTO);
    List<BoardDTO> findAllForAdmin();
    int countAll();
}
