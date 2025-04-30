package me.synology.freash97.board.mapper;

import me.synology.freash97.board.domain.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

/**
 * packageName   : me.synology.freash97.board.mapper
 * fileName      : BoardMapper
 * author        : iptable
 * date          : 2025-04-26
 * time          : 오전 12:08
 * description   :
 * ====================================================
 * DATE                  AUTHOR              NOTE
 * ----------------------------------------------------
 * 2025-04-26               iptab             최초 생성
 */

@Mapper
public interface BoardMapper {
    List<BoardDTO> findAll() throws SQLException;

    BoardDTO findById(Long boardSq) throws SQLException;

    void save(BoardDTO boardDTO) throws SQLException;

    void update(BoardDTO boardDTO) throws SQLException;

    void delete(Long boardSq) throws SQLException;
}
