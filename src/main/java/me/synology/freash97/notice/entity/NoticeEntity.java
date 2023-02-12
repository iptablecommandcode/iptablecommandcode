package me.synology.freash97.notice.entity;

import lombok.*;

import java.util.Date;

/**
 * date: 2023.02.06
 * name: NoticeEntity.java
 * desc: 게시글 entity 정의
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeEntity {
    private int noticeSeq;
    private int writer;
    private String title;
    private String content;
    private NoticeStatusCode noticeSttsCd;
    private Date createDate;
    private Date updateDate;
    private int operator;
}
