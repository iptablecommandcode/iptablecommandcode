package me.synology.freash97.Sign.Entity;

import lombok.Data;

import java.sql.Date;

@Data
public class UserEntity {
    private int OPERATION_NM;
    private String ID;
    private String PASSWORD;
    private String ACCOUNTAUTHLEVEL;
    private String NAME;
    private String EMAIL;
    private String PHONE;
    private int POSTCODE;
    private String ADDRESS1;
    private String ADDRESS2;
    private Date CREATION_DATE;
    private Date UPDATE_DATE;
    private String LAST_PAGE;
    private int LAST_OPERATION_NM;
}
