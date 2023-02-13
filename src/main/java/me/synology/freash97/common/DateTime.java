package me.synology.freash97.Common;

/*
auth : 박치원
title : 시간 등을 정의하기 위한 공통 파일
desc : 시간 온라인, 배치 시간, 게시글 시간을 정의함과 동시에 처리하기 위한 로직
*/

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;

@Slf4j
public class DateTime {
//    private String flag;
//    public DateTime (String flag){
//        this.flag = flag;
//    }

    public Timestamp CreateActionTime(){
        Timestamp time = new Timestamp(System.currentTimeMillis());

/*        if (flag.equals("O")){
            //DB에 저장되는 값
        } else if (flag.equals("B")) {
            //DB에 저장되는 값
        }*/

        return time;
    }
}
