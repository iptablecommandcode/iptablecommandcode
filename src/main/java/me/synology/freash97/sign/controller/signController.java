package me.synology.freash97.sign.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.synology.freash97.sign.domain.SignDTO;
import me.synology.freash97.sign.service.SignService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/login")
    public String loginForm() {
        return "login";  // login.html
    }

    @PostMapping("/signUp")
    public String signUp(@ModelAttribute SignDTO signDTO) throws Exception {
        log.debug("SignUp Controller Start !!!");

        signService.signUp(signDTO);

        log.info("SignUp Controller End !!!");
        return "redirect:/login";  // register.html
    }
}
