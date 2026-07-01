package me.synology.freash97.sign.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.synology.freash97.sign.domain.SignDTO;
import me.synology.freash97.sign.service.SignService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * packageName   : me.synology.freash97.sign.controller
 * fileName      : signController
 * author        : iptable
 * date          : 2025-04-22
 * ====================================================
 * DATE                  AUTHOR              NOTE
 * ----------------------------------------------------
 * 2025-04-22            iptab               최초 생성
 * 2025-12-07            iptab               checkUsername API 추가 (아이디 중복 체크)
 */
@Slf4j
@Controller
@RequestMapping("sign")
@RequiredArgsConstructor
public class signController {

    private final SignService signService;

    @GetMapping("/signIn")
    public String signIn() {
        return "sign/signIn";
    }

    @PostMapping("/signIn.do")
    public String signIn(@ModelAttribute SignDTO signDTO, HttpServletRequest request) throws Exception {
        SignDTO resultSignDTO = signService.findByUserAccount(signDTO);
        if (resultSignDTO != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", resultSignDTO);
            return "redirect:/index";
        } else {
            request.setAttribute("error", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "sign/signIn";
        }
    }

    @GetMapping("/signUp")
    public String signUp() {
        return "sign/signUp";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute SignDTO signDTO) throws Exception {
        log.debug("SignUp Controller Start !!!");
        signService.signUp(signDTO);
        log.info("SignUp Controller End !!!");
        return "redirect:/sign/signIn";
    }

    @GetMapping("/signOut")
    public String signOut(HttpSession session) throws Exception {
        session.invalidate();
        return "redirect:/index";
    }

    // 아이디 중복 체크 API (AJAX 호출용)
    // 사용 가능: true
    // 중복:     false
    @GetMapping("/checkUsername")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> checkUsername(@RequestParam String username) {
        boolean available = signService.checkUsername(username);
        return ResponseEntity.ok(Map.of("available", available));
    }

}
