package me.synology.freash97.board.vo.entity;

import lombok.Data;
import me.synology.freash97.common.entity.CommonDTO;

/**
 * packageName   : me.synology.freash97.board.vo.entity
 * fileName      : BoardDTO
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
public class BoardDTO extends CommonDTO {
    private Integer boardSq;
    private Integer userId;
    private String username;
    private String title;
    private String content;
}
