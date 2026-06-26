package me.synology.freash97.category.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.synology.freash97.common.entity.CommonDTO;

/**
 * packageName   : me.synology.freash97.category.domain
 * fileName      : CategoryDTO
 * author        : iptab
 * date          : 2025-06-24
 * ====================================================
 * DATE                  AUTHOR              NOTE
 * ----------------------------------------------------
 * 2025-06-24            iptab               최초 생성
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CategoryDTO extends CommonDTO {
    private Integer categorySq;
    private String  categoryName;
    private String  description;
    private Integer sortOrder;
    private String  useYn;
}
