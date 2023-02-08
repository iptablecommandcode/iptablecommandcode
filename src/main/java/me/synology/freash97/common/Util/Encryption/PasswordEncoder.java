package me.synology.freash97.Common.Util.Encryption;

public interface PasswordEncoder {
    String encode(CharSequence rawPassword);
    boolean matches(CharSequence rawPassword, String encodedPassword);
    boolean upgradeEncoding(String encodedPassword);
}
