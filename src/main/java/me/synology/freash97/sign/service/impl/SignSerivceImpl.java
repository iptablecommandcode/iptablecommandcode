package me.synology.freash97.sign.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.synology.freash97.sign.domain.SignDTO;
import me.synology.freash97.sign.mapper.SignMapper;
import me.synology.freash97.sign.service.SignService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * packageName   : me.synology.freash97.sign.service.impl
 * fileName      : SignSerivceImpl
 * author        : iptab
 * date          : 2025-04-28
 * time          : 오후 10:56
 * description   :
 * ====================================================
 * DATE                  AUTHOR              NOTE
 * ----------------------------------------------------
 * 2025-04-28               iptab             최초 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SignSerivceImpl implements SignService {

    private final SignMapper signMapper;

    @Override
    public void signUp(SignDTO signDTO) throws Exception {
        try {
            log.debug("SignUp Service Start !!!");

            Integer resultCount = signMapper.save(signDTO);
            Assert.notNull(resultCount, "SignUp Service Fail !!!");

            log.debug("SignUp Service End !!!");
        } catch (Exception e) {
            log.error("SignUp Service Fail !!!");
        }
    }

    @Override
    public SignDTO findByUsername(String username) throws Exception {
        SignDTO signDTO = new SignDTO();
        try {
            log.debug("findByUsername Service Start !!!");

            signDTO = signMapper.findByUsername(username);
            Assert.notNull(signDTO, "findByUsername Service Fail !!!");

            log.debug("findByUsername Service End !!!");
        } catch (Exception e) {
            log.error("findByUsername Service Fail !!!");
        }

        return signDTO;
    }
}
