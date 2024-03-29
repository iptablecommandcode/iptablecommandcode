package me.synology.freash97.sign.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.synology.freash97.common.DateTime;
import me.synology.freash97.common.FinalKey;
import me.synology.freash97.common.SeqCommon;
import me.synology.freash97.common.encryption.PasswordEncoder;
import me.synology.freash97.common.encryption.PasswordEncrtpyion;
import me.synology.freash97.member.mapper.MemberMapperReposiroty;
import me.synology.freash97.member.entity.MemberEntity;
import me.synology.freash97.member.service.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class SignService {
    public MemberMapperReposiroty memberMapperReposiroty;
    public SeqCommon seqCommon;

    /*
    수정 예정
    */
    public List<MemberEntity> selectMember() {
        List<MemberEntity> test = memberMapperReposiroty.memberSelect();

        return test;
    }
    /*
    auth : 박치원
    title : 멤버 생성
    desc : 멤버 생성을 위한 로직이다.
    */
    public MemberEntity createMember(Object obj) throws NullPointerException, Exception {
        MemberEntity memberEntity = new MemberEntity();
        PasswordEncoder encoder = new PasswordEncrtpyion();
        DateTime dateTime = new DateTime();
        Member member = new Member();

        Integer sequenceNumber = null;
        String password = null;


        log.debug("Mmeber 생성 시작");

        try {
            log.debug("화면에서 넘겨받은 값 입력");
            memberEntity = member.objToEntity(obj);

            //비밀번호 암호화
            password = (String) memberEntity.getPASSWORD();

            password = encoder.encode(password);

            memberEntity.setPASSWORD(password);

            log.debug("Mmeber Sequence 생성");
            sequenceNumber = seqCommon.createMemberSEQ();

            //시퀀스 입력
            memberEntity.setSEQ((int) sequenceNumber);

            //현재 시각 입력
            memberEntity.setCREATE_DATE(dateTime.CreateActionTime());
            
            log.debug("Register DB입력 실행");
            memberMapperReposiroty.memberAdd(memberEntity);


        } catch (NullPointerException e) {
            e.toString();
            log.debug("Member 생성 실패");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return memberEntity;
    }

    /**
    auth : 박치원
    title : 아이디 중복확인 서비스 로직
    desc : 아이디 중복확인을 위한 서비스 호출 로직
    */
    public boolean checkId(Object object) throws Exception, NullPointerException {
        FinalKey key = new FinalKey();
        MemberEntity memberEntity = new MemberEntity();
        Member member = new Member();

        boolean result = false;
        Integer idChkResult = 0;

        try {
            log.debug("service checkId 시작");
            memberEntity = member.objToEntity(object);

            idChkResult = memberMapperReposiroty.checkId(memberEntity);
            log.debug("service idChkResult 결과 : " + idChkResult);

            //0보다 크면 중복내용 있음
            if (idChkResult.equals(0)) {
                result = true;
            } else {
                result = false;
            }
            log.debug("service result 결과 : " + result);
        } catch (NullPointerException e) {
            log.error("서비스단 아이디 중복확인 중 오류 발생 NullPointerException");
            e.getMessage();
        } catch (Exception e) {
            log.error("서비스단 아이디 중복확인 중 오류 발생 Exception");
            e.getMessage();
        }

        return result;
    }

    /**
     * @desc Sign_In 동작 로그인시 확인한다
     * @param obj
     * @return MemberEntity
     */
    public MemberEntity sign_In(Object obj) throws Exception {
        MemberEntity member_Sign_In = new MemberEntity();
        MemberEntity member_DB = new MemberEntity();
        Member member = new Member();
        PasswordEncoder passwordEncoder = new PasswordEncrtpyion();

        try {
            //데이터 멤버에서 초기화
            member_Sign_In = member.objToEntity(obj);

            //DB에서 값 가져옴
            member_DB = memberMapperReposiroty.sign_In_Chk(member_Sign_In.getID());

            //아이디 및 비밀번호 값 확인
            if (member_Sign_In.getID().equals(member_DB.getID())){
                log.error("아이디가 일치하지 않습니다.");
                throw new RuntimeException();
            } else if(!passwordEncoder.decodeMatch(member_Sign_In.getPASSWORD(),member_DB.getPASSWORD())){
                log.error("비밀번호가 일치하지 않습니다.");
                throw new RuntimeException();
            } else {
                member_Sign_In.setPASSWORD("true");
            }

        } catch (Exception e){
            log.error("로그인 오류");
            throw new RuntimeException();
        }


        return member_Sign_In;
    }
}
