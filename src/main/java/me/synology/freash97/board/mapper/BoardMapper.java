package me.synology.freash97.board.mapper;

import me.synology.freash97.board.domain.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDTO> findAll();
    List<BoardDTO> findVisible(@Param("userId") Integer userId, @Param("adminYn") String adminYn);
    List<BoardDTO> findByCategory(int categorySq);
    List<BoardDTO> findVisibleByCategory(@Param("categorySq") int categorySq,
                                         @Param("userId") Integer userId,
                                         @Param("adminYn") String adminYn);
    List<BoardDTO> findByTag(int tagSq);
    List<BoardDTO> findVisibleByTag(@Param("tagSq") int tagSq,
                                    @Param("userId") Integer userId,
                                    @Param("adminYn") String adminYn);
    BoardDTO findById(int boardSq);
    BoardDTO findVisibleById(@Param("boardSq") int boardSq,
                             @Param("userId") Integer userId,
                             @Param("adminYn") String adminYn);
    void increaseViewCount(int boardSq);
    void save(BoardDTO boardDTO);
    void update(BoardDTO boardDTO);
    void delete(int boardSq);
    void updateNotice(BoardDTO boardDTO);
    List<BoardDTO> findAllForAdmin();
    int countAll();
}
