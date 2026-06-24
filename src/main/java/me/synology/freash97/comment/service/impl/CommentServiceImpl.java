package me.synology.freash97.comment.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.synology.freash97.comment.domain.CommentDTO;
import me.synology.freash97.comment.mapper.CommentMapper;
import me.synology.freash97.comment.service.CommentService;
import me.synology.freash97.sign.domain.SignDTO;
import me.synology.freash97.sign.service.SignService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName   : me.synology.freash97.comment.service.impl
 * fileName      : CommentServiceImpl
 * author        : iptab
 * date          : 2025-04-28
 * ====================================================
 * DATE                  AUTHOR              NOTE
 * ----------------------------------------------------
 * 2025-04-28            iptab               최초 생성
 * 2025-12-07            iptab               버그 수정
 *   - save() createUser 미설정 → setCreateUser() 추가
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final SignService signService;
    private final CommentMapper commentMapper;

    @Override
    public List<CommentDTO> findByBoardSq(Integer boardSq) throws Exception {
        log.debug("findByBoardSq Service Start !!!");
        log.debug("findByBoardSq Service boardSq : {}", boardSq);

        List<CommentDTO> commentDTOList = commentMapper.findByBoardSq(boardSq);

        log.debug("findByBoardSq commentDTOList : {}", commentDTOList);
        log.debug("findByBoardSq Service End !!!");
        return commentDTOList;
    }

    @Override
    public void save(CommentDTO commentDTO, String username) throws Exception {
        SignDTO signDTO = signService.findByUsername(username);
        commentDTO.setUserId(signDTO.getUserId());
        commentDTO.setUsername(username);
        // [수정] createUser 누락 → 추가
        commentDTO.setCreateUser(username);
        commentMapper.save(commentDTO);
    }

    @Override
    public void delete(Long commentSq) throws Exception {
        commentMapper.delete(commentSq);
    }
}
