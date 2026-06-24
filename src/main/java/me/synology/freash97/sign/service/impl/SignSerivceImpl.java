package me.synology.freash97.sign.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.synology.freash97.sign.domain.SignDTO;
import me.synology.freash97.sign.mapper.SignMapper;
import me.synology.freash97.sign.service.SignService;
import org.springframework.stereotype.Service;

/**
 * packageName   : me.synology.freash97.sign.service.impl
 * fileName      : SignSerivceImpl
 * author        : iptab
 * date          : 2025-04-28
 * ====================================================
 * DATE                  AUTHOR              NOTE
 * ----------------------------------------------------
 * 2025-04-28            iptab               최초 생성
 * 2025-12-07            iptab               버그 수정
 *   - findByUsername() 예외 삼킴 제거 → null 시 명시적 Exception throw
 *   - findByUserAccount() 동일 패턴 정리
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SignSerivceImpl implements SignService {

    private final SignMapper signMapper;

    @Override
    public void signUp(SignDTO signDTO) throws Exception {
        log.debug("SignUp Service Start !!!");
        Integer resultCount = signMapper.save(signDTO);
        if (resultCount == null || resultCount == 0) {
            throw new Exception("SignUp Service Fail: DB insert 실패");
        }
        log.debug("SignUp Service End !!!");
    }

    // [수정] 예외 삼킴 제거 - null 반환 대신 명시적 Exception throw
    @Override
    public SignDTO findByUsername(String username) throws Exception {
        log.debug("findByUsername Service Start !!!");

        SignDTO signDTO = signMapper.findByUsername(username);
        if (signDTO == null) {
            throw new Exception("존재하지 않는 사용자입니다: " + username);
        }

        log.debug("findByUsername Service End !!!");
        return signDTO;
    }

    @Override
    public SignDTO findByUserAccount(SignDTO signDTO) throws Exception {
        log.debug("findByUserAccount Service Start !!!");
        log.debug("findByUserAccount Service signDTO : {}", signDTO);

        SignDTO resultSignDTO = signMapper.findByUserAccount(signDTO);

        log.debug("findByUserAccount Service resultSignDTO : {}", resultSignDTO);
        log.debug("findByUserAccount Service End !!!");

        return resultSignDTO;  // 로그인 실패 시 null 반환 (Controller에서 null 체크)
    }
}
