package me.synology.freash97.Member.Service;

import lombok.extern.slf4j.Slf4j;
import me.synology.freash97.Member.Entity.MemberEntity;

import java.util.Date;
import java.util.Map;

/*
auth : 박치원
title : member 관련 로직 처리 클래스
desc : member 관련 로직을 처리하기 위한 클래스이다.
*/

@Slf4j
public class Member {

    public MemberEntity objToEntity(Object obj) {
        log.debug("Object로 들어온 Map의 값을 entity에 정렬한다.");
        MemberEntity memberEntity = new MemberEntity();
        Map<String, Object> param = (Map<String, Object>) obj;

        try {
            if (param.isEmpty()) {
                log.debug("Map 에 들어온 값이 없습니다.");
                return memberEntity;
            } else {
                for (int i = 0; i <= param.size(); i++) {
                    if (param.containsKey("SEQ")) {
                        memberEntity.setSEQ((int) param.get("SEQ"));
                    } else if (param.containsKey("ID")) {
                        memberEntity.setID((String) param.get("ID"));
                    } else if (param.containsKey("PASSWORD")) {
                        memberEntity.setPASSWORD((String) param.get("PASSWORD"));
                    } else if (param.containsKey("NAME")) {
                        memberEntity.setNAME((String) param.get("NAME"));
                    } else if (param.containsKey("EMAIL")) {
                        memberEntity.setEMAIL((String) param.get("EMAIL"));
                    } else if (param.containsKey("PHONE")) {
                        memberEntity.setPHONE((String) param.get("PHONE"));
                    } else if (param.containsKey("ZIPCODE")) {
                        memberEntity.setZIPCODE((int) param.get("ZIPCODE"));
                    } else if (param.containsKey("ROADARR")) {
                        memberEntity.setROADARR((String) param.get("ROADARR"));
                    } else if (param.containsKey("DETAILARR")) {
                        memberEntity.setDETAILARR((String) param.get("DETAILARR"));
                    } else if (param.containsKey("CREATE_DATE")) {
                        memberEntity.setCREATE_DATE((Date) param.get("CREATE_DATE"));
                    } else if (param.containsKey("UPDATE_DATE")) {
                        memberEntity.setUPDATE_DATE((Date) param.get("UPDATE_DATE"));
                    } else if (param.containsKey("OPERATOR")) {
                        memberEntity.setSEQ((int) param.get("OPERATOR"));
                    }
                }
            }
        } catch (NullPointerException e) {
            log.error("memberEntity 오류 발생");
            return memberClear(memberEntity);
        }


        log.debug("Map 에 들어온 값 처리 완료");
        return memberEntity;
    }

    /*
    auth : 박치원
    title : memberEntity Clear
    desc : memberEntity Clear처리를 하기 위한 로직이다.
    */
    public MemberEntity memberClear(MemberEntity member){
        Integer empty = null;

        member.setSEQ(empty);
        member.setID(null);
        member.setPASSWORD(null);
        member.setNAME(null);
        member.setEMAIL(null);
        member.setPHONE(null);
        member.setZIPCODE(empty);
        member.setROADARR(null);
        member.setDETAILARR(null);
        member.setCREATE_DATE(null);
        member.setUPDATE_DATE(null);
        member.setOPERATOR(empty);

        return member;
    }
}