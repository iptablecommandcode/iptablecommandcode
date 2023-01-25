package me.synology.freash97.Notice.Reposiroty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeMapperDTO {
    private int NOTICE_NUMBER;
    private int PATCH_COUNT;
    private String TITLE;
    private String NAME;
    private Date NOTICE_DATE;
    private Date CREATION_DATE;
    private Date UPDATE_DATE;
    private String LAST_PAGE;
    private int LAST_OPERATION_NM;
}
