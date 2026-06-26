package me.synology.freash97.emaildomain.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.synology.freash97.common.entity.CommonDTO;

/**
 * packageName   : me.synology.freash97.emaildomain.domain
 * fileName      : EmailDomainDTO
 * author        : iptab
 * date          : 2025-06-24
 * ====================================================
 * DATE                  AUTHOR              NOTE
 * ----------------------------------------------------
 * 2025-06-24            iptab               최초 생성
 *   - 이메일 도메인 관리 테이블(EMAIL_DOMAIN) DTO
 *   - 추후 관리자 페이지에서 CRUD 처리 예정
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EmailDomainDTO extends CommonDTO {

    private Integer domainSq;   // PK (AUTO_INCREMENT)
    private String  domainName; // 도메인명 (예: naver.com)
    private String  useYn;      // 사용 여부 (Y/N) - 관리자 페이지에서 비활성화 처리용

}
