package me.synology.freash97.tag.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.synology.freash97.common.entity.CommonDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class TagDTO extends CommonDTO {
    private Integer tagSq;
    private String  tagName;
    private String  useYn;
}
