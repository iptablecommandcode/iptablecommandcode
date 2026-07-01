package me.synology.freash97.sign.service.impl;

import lombok.RequiredArgsConstructor;
import me.synology.freash97.sign.domain.SignDTO;
import me.synology.freash97.sign.mapper.SignMapper;
import me.synology.freash97.sign.service.SignService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class SignSerivceImpl implements SignService {

    private static final Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2[aby]\\$\\d\\d\\$[./0-9A-Za-z]{53}\\z");

    private final SignMapper signMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SignDTO findByUsername(String username) {
        return signMapper.findByUsername(username);
    }

    @Override
    public SignDTO findByUserAccount(SignDTO signDTO) {
        if (signDTO == null || signDTO.getUsername() == null || signDTO.getPassword() == null) {
            return null;
        }

        SignDTO user = signMapper.findByUsername(signDTO.getUsername());
        if (user == null) {
            return null;
        }

        String rawPassword = signDTO.getPassword();
        String savedPassword = user.getPassword();
        if (isBCryptPassword(savedPassword)) {
            if (!passwordEncoder.matches(rawPassword, savedPassword)) {
                return null;
            }
            user.setPassword(null);
            return user;
        }

        if (!rawPassword.equals(savedPassword)) {
            return null;
        }

        String encodedPassword = passwordEncoder.encode(rawPassword);
        signMapper.updatePassword(user.getUserId(), encodedPassword);
        user.setPassword(null);
        return user;
    }

    @Override
    public void save(SignDTO signDTO) {
        encodePassword(signDTO);
        signMapper.save(signDTO);
    }

    @Override
    public void signUp(SignDTO signDTO) {
        save(signDTO);
    }

    @Override
    public boolean checkUsername(String username) {
        return signMapper.checkUsername(username) == 0;
    }

    @Override
    public List<SignDTO> findAll() {
        return signMapper.findAll();
    }

    @Override
    public void updateAdmin(int userId, String adminYn) {
        signMapper.updateAdmin(userId, adminYn);
    }

    @Override
    public int countAll() {
        return signMapper.countAll();
    }

    @Override
    public void deleteUser(int userId) {
        signMapper.deleteUser(userId);
    }

    private void encodePassword(SignDTO signDTO) {
        if (signDTO == null || signDTO.getPassword() == null) {
            throw new IllegalArgumentException("Password is required.");
        }
        signDTO.setPassword(passwordEncoder.encode(signDTO.getPassword()));
    }

    private boolean isBCryptPassword(String password) {
        return password != null && BCRYPT_PATTERN.matcher(password).matches();
    }
}
