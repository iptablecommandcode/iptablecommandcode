package me.synology.freash97.common.Util.Encryption;
/*
암복호화를 사용하기 위한 인터페이스 이다.
*/

public interface PasswordEncoder {
    String encode(CharSequence rawPassword);
    boolean decodeMatch(CharSequence rawPassword, String encodedPassword);
    boolean upgradeEncoding(String encodedPassword);
}
