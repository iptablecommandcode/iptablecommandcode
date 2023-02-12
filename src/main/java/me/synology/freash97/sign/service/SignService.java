package me.synology.freash97.Sign.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.synology.freash97.Common.SeqCommon;
import me.synology.freash97.Member.Mapper.MemberMapperReposiroty;
import me.synology.freash97.Member.Entity.MemberEntity;
import me.synology.freash97.Member.Service.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class SignService {
    public MemberMapperReposiroty memberMapperReposiroty;

    /*
    수정 예정
    */
    public List<MemberEntity> selectMember(){
        List<MemberEntity> test = memberMapperReposiroty.memberSelect();

        return test;
    }
    /*
    auth : 박치원
    title : 멤버 생성
    desc : 멤버 생성을 위한 로직이다.
    */
    public MemberEntity createMember(Object obj){
        MemberEntity memberEntity = null;
        Member member = new Member();
        SeqCommon seqCommon = new SeqCommon();
        Integer sequenceNumber;

        log.debug("Mmeber 생성 시작");

        try {
            log.debug("Mmeber Sequence 생성");
            sequenceNumber = seqCommon.createMemberSEQ();

            memberEntity.setSEQ(sequenceNumber);

            log.debug("화면에서 넘겨받은 값 입력");
            memberEntity = member.objToEntity(obj);

            memberMapperReposiroty.memberAdd(memberEntity);


        } catch (Exception e){

        } finally {
            log.debug("Member 생성 성공");
        }


        return memberEntity;
    }
}
