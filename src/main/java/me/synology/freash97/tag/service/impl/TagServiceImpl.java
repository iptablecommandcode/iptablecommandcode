package me.synology.freash97.tag.service.impl;

import me.synology.freash97.tag.domain.TagDTO;
import me.synology.freash97.tag.mapper.TagMapper;
import me.synology.freash97.tag.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<TagDTO> findAll() {
        return tagMapper.findAll();
    }

    @Override
    public List<TagDTO> findAllActive() {
        return tagMapper.findAllActive();
    }

    @Override
    public List<TagDTO> findByBoardSq(int boardSq) {
        return tagMapper.findByBoardSq(boardSq);
    }

    @Override
    public TagDTO findByName(String tagName) {
        return tagMapper.findByName(tagName);
    }

    @Override
    public void save(TagDTO tagDTO) {
        tagMapper.save(tagDTO);
    }

    @Override
    public void saveBoardTag(int boardSq, int tagSq) {
        tagMapper.saveBoard_Tag(boardSq, tagSq);
    }

    @Override
    public void deleteByBoardSq(int boardSq) {
        tagMapper.deleteByBoardSq(boardSq);
    }

    @Override
    public void delete(int tagSq) {
        tagMapper.delete(tagSq);
    }
}
