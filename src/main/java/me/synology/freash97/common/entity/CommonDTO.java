package me.synology.freash97.common.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * packageName   : me.synology.freash97.common.entity
 * fileName      : CommonEntity
 * author        : iptab
 * date          : 2025-04-26
 * time          : 오후 5:34
 * description   :
 * ====================================================
 * DATE                  AUTHOR              NOTE
 * ----------------------------------------------------
 * 2025-04-26               iptab             최초 생성
 */
@Data
public class CommonDTO {
    private String createUser;
    private LocalDateTime createDate;
    private String updateUser;
    private LocalDateTime updateDate;
}
