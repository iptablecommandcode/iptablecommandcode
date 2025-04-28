package me.synology.freash97.sign.service;

import me.synology.freash97.sign.domain.SignDTO;

/**
 * packageName   : me.synology.freash97.sign.service
 * fileName      : SignService
 * author        : iptab
 * date          : 2025-04-28
 * time          : 오후 10:56
 * description   :
 * ====================================================
 * DATE                  AUTHOR              NOTE
 * ----------------------------------------------------
 * 2025-04-28               iptab             최초 생성
 */
public interface SignService {
    void signUp(SignDTO signDTO) throws Exception;

    SignDTO findByUsername(String username) throws Exception;
}
