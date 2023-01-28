CREATE TABLE USER_TABLE(
                           OPERATION_NM INT NOT NULL,
                           ID VARCHAR(15) NOT NULL,
                           PASSWORD VARCHAR(40) NOT NULL,
                           ACCOUNTAUTHLEVEL VARCHAR(1),
                           NAME VARCHAR(10) NOT NULL,
                           EMAIL VARCHAR(50),
                           PHONE VARCHAR(14),
                           POSTCODE INT,
                           ADDRESS1 VARCHAR(100),
                           ADDRESS2 VARCHAR(100),
                           CREATION_DATE DATETIME,
                           UPDATE_DATE DATETIME,
                           LAST_PAGE VARCHAR(30),
                           LAST_OPERATION_NM INT,
                           PRIMARY KEY (ID)
);
CREATE TABLE USER_TABLE_HIST(
                                DELETE_DATE DATETIME NOT NULL,
                                OPERATION_NM INT NOT NULL,
                                ID VARCHAR(15) NOT NULL,
                                PASSWORD VARCHAR(40) NOT NULL,
                                ACCOUNTAUTHLEVEL VARCHAR(1),
                                NAME VARCHAR(10) NOT NULL,
                                EMAIL VARCHAR(50),
                                PHONE VARCHAR(14),
                                POSTCODE INT,
                                ADDRESS1 VARCHAR(100),
                                ADDRESS2 VARCHAR(100),
                                CREATION_DATE DATETIME,
                                UPDATE_DATE DATETIME,
                                LAST_PAGE VARCHAR(30),
                                LAST_OPERATION_NM INT,
                                PRIMARY KEY (ID)
);

CREATE TABLE NOTICE_TABLE(
                             NOTICE_NUMBER INT,
                             PATCH_COUNT INT,
                             TITLE VARCHAR(100) NOT NULL,
                             NAME VARCHAR(10),
                             NOTICE_DATE DATETIME NOT NULL,
                             CREATION_DATE DATETIME NOT NULL,
                             UPDATE_DATE DATETIME NOT NULL,
                             LAST_PAGE VARCHAR(30),
                             LAST_OPERATION_NM INT,
                             PRIMARY KEY(NOTICE_NUMBER)
);

CREATE TABLE NOTICE_TABLE_HIST(
                                  DELETE_DATE DATETIME NOT NULL,
                                  NOTICE_NUMBER INT,
                                  PATCH_COUNT INT,
                                  TITLE VARCHAR(100) NOT NULL,
                                  NAME VARCHAR(10),
                                  NOTICE_DATE DATETIME NOT NULL,
                                  CREATION_DATE DATETIME NOT NULL,
                                  UPDATE_DATE DATETIME NOT NULL,
                                  LAST_PAGE VARCHAR(30),
                                  LAST_OPERATION_NM INT,
                                  PRIMARY KEY(NOTICE_NUMBER)
);

CREATE SEQUENCE USER_OPER_SEQ
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 100000;

CREATE SEQUENCE NOTICE_SEQ
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 100000;