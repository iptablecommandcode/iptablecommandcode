package me.synology.freash97.tag.mapper;

import me.synology.freash97.tag.domain.TagDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagMapper {
    List<TagDTO> findAll();
    List<TagDTO> findAllActive();
    List<TagDTO> findByBoardSq(int boardSq);
    TagDTO findByName(String tagName);
    void save(TagDTO tagDTO);
    void saveBoard_Tag(@Param("boardSq") int boardSq, @Param("tagSq") int tagSq);
    void deleteByBoardSq(int boardSq);
    void delete(int tagSq);
}
