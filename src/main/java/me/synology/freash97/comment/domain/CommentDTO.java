package me.synology.freash97.comment.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.synology.freash97.common.entity.CommonDTO;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class CommentDTO extends CommonDTO {
    private Integer          commentSq;
    private Integer          boardSq;
    private Integer          userId;
    private Integer          parentCommentSq;   // 대댓글용
    private String           username;
    private String           content;
    private List<CommentDTO> replies;            // 대댓글 목록
}
