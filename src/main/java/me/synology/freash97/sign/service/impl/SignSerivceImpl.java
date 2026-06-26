package me.synology.freash97.sign.service.impl;

import me.synology.freash97.sign.domain.SignDTO;
import me.synology.freash97.sign.mapper.SignMapper;
import me.synology.freash97.sign.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignSerivceImpl implements SignService {

    @Autowired
    private SignMapper signMapper;

    @Override
    public SignDTO findByUsername(String username) {
        return signMapper.findByUsername(username);
    }

    @Override
    public SignDTO findByUserAccount(SignDTO signDTO) {
        return signMapper.findByUserAccount(signDTO);
    }

    @Override
    public void save(SignDTO signDTO) {
        signMapper.save(signDTO);
    }

    @Override
    public void signUp(SignDTO signDTO) {
        signMapper.save(signDTO);
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
}
