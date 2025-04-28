package me.synology.freash97.board.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.synology.freash97.board.mapper.BoardMapper;
import me.synology.freash97.board.service.BoardService;
import me.synology.freash97.board.vo.entity.BoardDTO;
import me.synology.freash97.sign.domain.SignDTO;
import me.synology.freash97.sign.mapper.SignMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName   : me.synology.freash97.board.service.impl
 * fileName      : boardServiceImpl
 * author        : iptab
 * date          : 2025-04-25
 * time          : 오후 11:47
 * description   :
 * ====================================================
 * DATE                  AUTHOR              NOTE
 * ----------------------------------------------------
 * 2025-04-25               iptab             최초 생성
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;
    private final SignMapper signMapper;

    @Override
    public List<BoardDTO> findAll() throws Exception {
        return boardMapper.findAll();
    }

    @Override
    public BoardDTO findById(Long boardSq) throws Exception {
        return boardMapper.findById(boardSq);
    }

    @Override
    public void save(BoardDTO boardDTO, String username) throws Exception {
        SignDTO signDTO = signMapper.findByUsername(username);
        boardDTO.setUserId(signDTO.getUser_id());
        boardDTO.setUsername(username);
        boardMapper.save(boardDTO);
    }

    @Override
    public void update(BoardDTO boardDTO) throws Exception {
        boardMapper.update(boardDTO);
    }

    @Override
    public void delete(Long boardSq) throws Exception {
        boardMapper.delete(boardSq);
    }
}
