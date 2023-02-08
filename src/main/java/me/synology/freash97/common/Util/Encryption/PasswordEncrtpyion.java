package me.synology.freash97.Common.Util.Encryption;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
public class PasswordEncrtpyion implements PasswordEncoder{
    @Override
    public String encode(CharSequence rawPassword) {
        log.debug("PasswordEncrtpyion 시작");
        BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder(11);
        String encoding = null;

        if (rawPassword == null){
            throw new IllegalArgumentException("encode is null : 암호화 내용은 null을 할 수 없습니다.");
        }

        encoding = bcryptEncoder.encode(rawPassword);


        log.debug("PasswordEncrtpyion 종료");
        return encoding;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        log.debug("PasswordDecryption 시작");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(11);
        boolean check;

        check = bCryptPasswordEncoder.matches(rawPassword, encodedPassword);

        log.debug("PasswordDecryption 결과" + check);

        log.debug("PasswordDecryption 종료");
        return check;
    }
    @Override
    public boolean upgradeEncoding(String encodedPassword){
        return false;
    }
}
