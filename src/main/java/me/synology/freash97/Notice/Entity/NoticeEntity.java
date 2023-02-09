package me.synology.freash97.Notice.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class NoticeEntity {
    private int NOTICE_SEQ;
    private int WRITER;
    private String TITLE;
    private String DETAIL;
    private String NOTICE_STTS_CD;
    private Date CREATE_DATE;
    private Date UPDATE_DATE;
    private int OPERATOR;
}
