package me.synology.freash97.member.repository;

import me.synology.freash97.member.entity.MemberEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapperReposiroty {
    public List<MemberEntity> memberSelect();
}
