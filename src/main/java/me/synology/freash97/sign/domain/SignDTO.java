package me.synology.freash97.sign.domain;

import lombok.Data;
import me.synology.freash97.common.entity.CommonDTO;

/**
 * packageName   : me.synology.freash97.board.vo.entity
 * fileName      : SignEntity
 * author        : iptab
 * date          : 2025-04-26
 * time          : 오전 12:10
 * description   :
 * ====================================================
 * DATE                  AUTHOR              NOTE
 * ----------------------------------------------------
 * 2025-04-26               iptab             최초 생성
 */

@Data
public class SignDTO extends CommonDTO {
    private Integer user_id;
    private String username;
    private String password;
    private String email;
    private String admin;
}
