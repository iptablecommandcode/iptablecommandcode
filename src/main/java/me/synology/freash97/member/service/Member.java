package me.synology.freash97.member.service;

import lombok.extern.slf4j.Slf4j;
import me.synology.freash97.member.entity.MemberEntity;

import java.sql.Timestamp;
import java.util.Map;

/*
auth : 박치원
title : member 관련 로직 처리 클래스
desc : member 관련 로직을 처리하기 위한 클래스이다.
*/

@Slf4j
public class Member {

    public MemberEntity objToEntity(Object obj) throws Exception,NullPointerException{
        log.debug("objToEntity에서 Object로 들어온 Map의 값을 entity에 정렬한다.");
        log.debug("objToEntity에서 Object 출력 : "+obj.toString());
        MemberEntity memberEntity = new MemberEntity();
        Map<String, Object> param = (Map<String, Object>) obj;
        String checkValue = null; //if문 내에서 값 오류 발생시 점검용 변수

        try {
            if (param.isEmpty()) {
                log.debug("objToEntity에서 Map 에 들어온 값이 없습니다.");
                return memberEntity;
            } else {
                if (param.containsKey("SEQ")) {
                    checkValue = "SEQ";
                    memberEntity.setSEQ((int) param.get("SEQ"));
                }
                if (param.containsKey("id")) {
                    checkValue = "id";
                    memberEntity.setID((String) param.get("id"));
                }
                if (param.containsKey("password")) {
                    checkValue = "password";
                    memberEntity.setPASSWORD((String) param.get("password"));
                    
                }
                if (param.containsKey("name")) {
                    checkValue = "name";
                    memberEntity.setNAME((String) param.get("name"));
                }
                if (param.containsKey("email")) {
                    checkValue = "email";
                    memberEntity.setEMAIL((String) param.get("email"));
                }
                if (param.containsKey("phone")) {
                    checkValue = "phone";
                    memberEntity.setPHONE((String) param.get("phone"));
                }
                if (param.containsKey("zipcode")) {
                    if ("".equals(param.get("zipcode"))) {
                        checkValue = "zipcode";
                        memberEntity.setZIPCODE(null);
                    } else if (param.get("zipcode") instanceof String) {
                        checkValue = "zipcode";
                        memberEntity.setZIPCODE(Integer.parseInt(String.valueOf(param.get("zipcode"))));
                    } else {
                        checkValue = "zipcode";
                        memberEntity.setZIPCODE((int) param.get("zipcode"));
                    }
                }
                if (param.containsKey("roadaddr")) {
                    checkValue = "roadaddr";
                    memberEntity.setROADADDR((String) param.get("roadaddr"));
                }
                if (param.containsKey("detailaddr")) {
                    checkValue = "detailaddr";
                    memberEntity.setDETAILADDR((String) param.get("detailaddr"));
                }
                if (param.containsKey("create_date")) {
                    checkValue = "create_date";
                    memberEntity.setCREATE_DATE((Timestamp) param.get("create_date"));
                }
                if (param.containsKey("update_date")) {
                    checkValue = "update_date";
                    memberEntity.setUPDATE_DATE((Timestamp) param.get("update_date"));
                }
                if (param.containsKey("operator")) {
                    checkValue = "operator";
                    memberEntity.setSEQ((int) param.get("operator"));
                }
                log.debug("objToEntity에서 memberEntity 출력 : "+memberEntity.toString());
                log.debug("objToEntity에서 Map 에 들어온 값 처리 완료");
            }
        } catch (NullPointerException e) {
            log.error("objToEntity에서" + checkValue + " memberEntity 오류 발생 NullPointerException");
            throw new RuntimeException(e);
        } catch (Exception e){
            log.error("objToEntity에서" + checkValue + " memberEntity 오류 발생 Exception");
            throw new RuntimeException(e);
        }

        return memberEntity;
    }

    /*
    auth : 박치원
    title : memberEntity Clear
    desc : memberEntity Clear처리를 하기 위한 로직이다.
    */
    public MemberEntity memberClear(MemberEntity member) {
        Integer empty = null;

        log.debug("memberClear 동작");
        member.setSEQ(empty);
        member.setID(null);
        member.setPASSWORD(null);
        member.setNAME(null);
        member.setEMAIL(null);
        member.setPHONE(null);
        member.setZIPCODE(empty);
        member.setROADADDR(null);
        member.setDETAILADDR(null);
        member.setCREATE_DATE(null);
        member.setUPDATE_DATE(null);
        member.setOPERATOR(empty);

        return member;
    }
}