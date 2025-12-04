package me.synology.freash97.sign.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.synology.freash97.sign.domain.SignDTO;
import me.synology.freash97.sign.service.SignService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * packageName   : me.synology.freash97.sign.controller
 * fileName      : signController
 * author        : iptable
 * date          : 2025-04-22
 * time          : 오후 11:49
 * description   : 계정 관련
 * ====================================================
 * DATE                  AUTHOR              NOTE
 * ----------------------------------------------------
 * 2025-04-22               iptab             최초 생성
 */

@Slf4j
@Controller
@RequestMapping("sign")
@RequiredArgsConstructor
public class signController {

    private final SignService signService;

    //로그인 페이지 실행
    @GetMapping("/signIn")
    public String signIn() {
        return "sign/signIn";  // signIn.html
    }

    //로그인 처리 이후 메인 index 페이지로 이동
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

    //회원 가입 페이지
    @GetMapping("/signUp")
    public String signUp() {
        return "sign/signUp";
    }

    //계정 생성
    @PostMapping("/register")
    public String register(@ModelAttribute SignDTO signDTO) throws Exception {
        log.debug("SignUp Controller Start !!!");

        signService.signUp(signDTO);

        log.info("SignUp Controller End !!!");
        return "redirect:/sign/signIn";  // register.html
    }

    @GetMapping("/signOut")
    public String signOut(HttpSession session) throws Exception {
        session.invalidate();
        return "redirect:/index";
    }
}
