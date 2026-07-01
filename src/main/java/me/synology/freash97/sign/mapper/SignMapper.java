package me.synology.freash97.sign.mapper;

import me.synology.freash97.sign.domain.SignDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SignMapper {
    SignDTO findByUsername(String username);
    void save(SignDTO signDTO);
    int checkUsername(String username);
    List<SignDTO> findAll();
    void updatePassword(@Param("userId") int userId, @Param("password") String password);
    void updateAdmin(@Param("userId") int userId, @Param("adminYn") String adminYn);
    int countAll();
    void deleteUser(int userId);
}
