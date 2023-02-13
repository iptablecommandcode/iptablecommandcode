package me.synology.freash97.Common.Encryption;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
암호화 복호화가 진행되는 클래스
*/

@Slf4j
public class PasswordEncrtpyion implements PasswordEncoder {
    /*
    auth : 박치원
    title : 암호화
    desc : 특정 문자를 암호화 할 때 사용하는 메소드
    */
    @Override
    public String encode(CharSequence rawPassword) throws NullPointerException {
        log.debug("PasswordEncrtpyion 시작");
        BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder(11);
        String encoding = null;

        try {
            if (rawPassword == null) {
                throw new IllegalArgumentException("encode is null : 암호화 내용은 null을 할 수 없습니다.");
            }

            encoding = bcryptEncoder.encode(rawPassword);


            log.debug("PasswordEncrtpyion 종료");
        } catch (NullPointerException e) {
            log.debug("암호화 실패");
            e.getMessage();
        }

        return encoding;
    }

    /*
    auth : 박치원
    title : 복호화
    desc : 암호화된 내용을 확인하여 결과를 리턴한다.
    */
    @Override
    public boolean decodeMatch(CharSequence rawPassword, String encodedPassword) throws NullPointerException {
        log.debug("PasswordMatch 시작");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(11);
        boolean check = false;

        try {
            check = bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
        } catch (NullPointerException e) {
            log.debug("PasswordMatch 실패");
            e.getMessage();
        }

        log.debug("PasswordMatch 결과" + check);

        log.debug("PasswordMatch 종료");
        return check;
    }

    /*
    auth : 박치원
    title : 암호화 업그레이드
    desc : 암호화 된 내용을 다시 암호화 하여 보안성을 높힐 용도이나 사용하지 않을 예정이다.
    */
    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return false;
    }
}
