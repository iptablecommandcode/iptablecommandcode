package me.synology.freash97.comment.domain;

import lombok.Data;
import me.synology.freash97.common.entity.CommonDTO;

/**
 * packageName   : me.synology.freash97.comment.domain
 * fileName      : CommentDTO
 * author        : iptab
 * date          : 2025-04-28
 * time          : 오후 10:52
 * description   :
 * ====================================================
 * DATE                  AUTHOR              NOTE
 * ----------------------------------------------------
 * 2025-04-28               iptab             최초 생성
 */

@Data
public class CommentDTO extends CommonDTO {
    private Long commentSq;
    private Long boardSq;
    private Long userId;
    private String username;
    private String content;
}
