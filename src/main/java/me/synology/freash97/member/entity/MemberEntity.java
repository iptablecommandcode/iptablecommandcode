package me.synology.freash97.Member.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberEntity {
    private int SEQ;
    private String ID;
    private String PASSWORD;
    private String NAME;
    private String EMAIL;
    private String PHONE;
    private int ZIPCODE;
    private String ROADADDR;
    private String DETAILADDR;
    private Timestamp CREATE_DATE;
    private Timestamp UPDATE_DATE;
    private int OPERATOR;
}
