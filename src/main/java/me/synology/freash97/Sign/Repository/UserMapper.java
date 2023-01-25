package me.synology.freash97.Sign.Repository;

import me.synology.freash97.Sign.Entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<UserEntity> UserSelect();
}
