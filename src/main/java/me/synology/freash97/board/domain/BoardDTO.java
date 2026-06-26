package me.synology.freash97.board.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.synology.freash97.common.entity.CommonDTO;
import me.synology.freash97.tag.domain.TagDTO;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class BoardDTO extends CommonDTO {
    private Integer      boardSq;
    private Integer      userId;
    private Integer      categorySq;
    private String       categoryName;
    private String       username;
    private String       title;
    private String       content;
    private String       summary;          // 목록 미리보기
    private String       thumbnailUrl;     // 썸네일
    private Integer      viewCount;
    private String       noticeYn;
    private String       useYn;
    private List<TagDTO> tags;             // 태그 목록 (JOIN용)
    private String       tagNames;         // 글쓰기 폼 입력용 (쉼표 구분)
}
