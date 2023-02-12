package me.synology.freash97.Member.Mapper;

import me.synology.freash97.Member.Entity.MemberEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
    auth : 박치원
    title : Mybatis Member 접근용
    desc : Mybatis Member에 쿼리문 접근하기 위한 Mapper클래스이다.
*/

@Mapper
public interface MemberMapperReposiroty {
    /*
    수정 예정
    */
    public List<MemberEntity> memberSelect();

    /*
    수정 예정
    */
    public MemberEntity memberSignIn(MemberEntity memberEntity);

    /*
    auth : 박치원
    title : register시 멤버 생성
    desc : register시 멤버 생성을 위한 매퍼
    */
    public void memberAdd(MemberEntity memberEntity);

    /*
    auth : 박치원
    title : 시퀀스 번호 생성
    desc : 시퀀스 번호 생성을 위한 매퍼
    */
    public Integer createSEQ();
}