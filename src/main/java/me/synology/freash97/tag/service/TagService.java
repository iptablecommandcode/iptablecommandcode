package me.synology.freash97.tag.service;

import me.synology.freash97.tag.domain.TagDTO;

import java.util.List;

public interface TagService {
    List<TagDTO> findAll();
    List<TagDTO> findAllActive();
    List<TagDTO> findByBoardSq(int boardSq);
    TagDTO findByName(String tagName);
    void save(TagDTO tagDTO);
    void saveBoardTag(int boardSq, int tagSq);
    void deleteByBoardSq(int boardSq);
    void delete(int tagSq);
}
