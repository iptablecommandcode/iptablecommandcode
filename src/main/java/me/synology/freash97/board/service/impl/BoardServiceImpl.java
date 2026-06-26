package me.synology.freash97.board.service.impl;

import me.synology.freash97.board.domain.BoardDTO;
import me.synology.freash97.board.mapper.BoardMapper;
import me.synology.freash97.board.service.BoardService;
import me.synology.freash97.tag.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<BoardDTO> findAll() {
        return boardMapper.findAll();
    }

    @Override
    public List<BoardDTO> findByCategory(int categorySq) {
        return boardMapper.findByCategory(categorySq);
    }

    @Override
    public List<BoardDTO> findByTag(int tagSq) {
        return boardMapper.findByTag(tagSq);
    }

    @Override
    public BoardDTO findById(int boardSq) {
        return boardMapper.findById(boardSq);
    }

    @Override
    public void increaseViewCount(int boardSq) {
        boardMapper.increaseViewCount(boardSq);
    }

    @Override
    public void save(BoardDTO boardDTO, String username) {
        boardDTO.setCreateUser(username);
        boardMapper.save(boardDTO);
    }

    @Override
    public void update(BoardDTO boardDTO, String username) {
        boardDTO.setUpdateUser(username);
        boardMapper.update(boardDTO);
    }

    @Override
    public void delete(int boardSq) {
        boardMapper.delete(boardSq);
    }

    @Override
    public void updateNotice(BoardDTO boardDTO) {
        boardMapper.updateNotice(boardDTO);
    }

    @Override
    public List<BoardDTO> findAllForAdmin() {
        return boardMapper.findAllForAdmin();
    }

    @Override
    public int countAll() {
        return boardMapper.countAll();
    }
}
