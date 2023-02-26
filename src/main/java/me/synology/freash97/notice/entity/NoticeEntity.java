package me.synology.freash97.notice.entity;

import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

/**
 * date: 2023.02.19
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
    private char noticeSttsCd;
    private Timestamp createDate;
    private Timestamp updateDate;
    private int operator;

    public NoticeEntity(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
