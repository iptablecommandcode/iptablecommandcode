package me.synology.freash97.Sign.Service;

import me.synology.freash97.Sign.Entity.UserEntity;
import me.synology.freash97.Sign.Repository.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserMapper userMapper;
    public  UserService(UserMapper userMapper){
        this.userMapper = userMapper;
    }
    public List<UserEntity> selectUser(){

        List<UserEntity> returnTest = userMapper.UserSelect();

        return returnTest;
    }
}
