package me.synology.freash97.sign.service;

import me.synology.freash97.sign.domain.SignDTO;

import java.util.List;

public interface SignService {
    SignDTO findByUsername(String username);
    SignDTO findByUserAccount(SignDTO signDTO);
    void save(SignDTO signDTO);
    void signUp(SignDTO signDTO);
    boolean checkUsername(String username);
    List<SignDTO> findAll();
    void updateAdmin(int userId, String adminYn);
    int countAll();
    void deleteUser(int userId);
}
