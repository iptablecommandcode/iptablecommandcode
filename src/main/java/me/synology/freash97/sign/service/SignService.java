package me.synology.freash97.sign.service;

import lombok.AllArgsConstructor;
import me.synology.freash97.member.entity.MemberEntity;
import me.synology.freash97.member.repository.MemberMapperReposiroty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SignService {
    public MemberMapperReposiroty memberMapperReposiroty;

    public List<MemberEntity> selectMember(){
        List<MemberEntity> test = memberMapperReposiroty.memberSelect();

        return test;
    }
}
