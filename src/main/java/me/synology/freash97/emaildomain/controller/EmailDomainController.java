package me.synology.freash97.emaildomain.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.synology.freash97.emaildomain.domain.EmailDomainDTO;
import me.synology.freash97.emaildomain.service.EmailDomainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * packageName   : me.synology.freash97.emaildomain.controller
 * fileName      : EmailDomainController
 * author        : iptab
 * date          : 2025-06-24
 * ====================================================
 * DATE                  AUTHOR              NOTE
 * ----------------------------------------------------
 * 2025-06-24            iptab               최초 생성
 *   - GET /api/email-domain/list : 활성 도메인 목록 반환 (AJAX 용)
 *   - 추후 관리자 페이지 CRUD API 추가 예정
 */
@Slf4j
@RestController
@RequestMapping("/api/email-domain")
@RequiredArgsConstructor
public class EmailDomainController {

    private final EmailDomainService emailDomainService;

    // 회원가입 페이지에서 도메인 목록 AJAX 조회
    @GetMapping("/list")
    public ResponseEntity<List<EmailDomainDTO>> findAllActive() {
        List<EmailDomainDTO> list = emailDomainService.findAllActive();
        return ResponseEntity.ok(list);
    }

}
