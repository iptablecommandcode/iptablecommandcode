package me.synology.freash97.common;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.synology.freash97.member.mapper.MemberMapperReposiroty;
import org.springframework.stereotype.Service;

/*
Auth : 박치원
Title : 시퀀스 관리 객체
Desc : 시퀀스 번호를 관리하는 공통 객체이다.
*/
@Slf4j
@Service
@AllArgsConstructor
public class SeqCommon {

    public MemberMapperReposiroty member;


    /*
    Auth : 박치원
    Title : Member 시퀀스 생성
    Desc : Member 시퀀스 번호를 생성하는 로직이다. 시퀀스 번호가 중복 생성될 경우를 대비해 중복 확인 로직추가
    */
    public Integer createMemberSEQ() throws NullPointerException {
        log.debug("MemberSEQ 생성 시작");
        Integer createSEQ = null;
        boolean loop = true;

        try {
            while (loop) {
                createSEQ = member.createSEQ();

                log.debug("생성된 SEQ번호 : " + createSEQ);

                if (createSEQ.equals(null)) {
                    log.debug("시퀀스 생성 실패");
                    throw new NullPointerException("시퀀스 값이 비어있습니다.");
                } else if (createSEQ == 1) {
                    log.debug("시퀀스 생성 실패");
                    loop = true;
                } else {
                    log.debug("시퀀스 생성 성공");
                    loop = false;
                }
            }
        } catch (Exception e) {
            log.debug("시퀀스 생성 실패");
            e.toString();
        }

        return createSEQ;
    }
}