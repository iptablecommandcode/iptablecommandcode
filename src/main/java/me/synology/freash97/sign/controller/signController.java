package me.synology.freash97.sign.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
public class signController {

    @GetMapping("/signIn")
    public String signIn() {
        log.debug("signIn Controller Start !!!");

        return "sign/signIn";
    }

    @GetMapping("/signUp")
    public String signUp() {
        log.debug("signUp Controller Start !!!");

        return "sign/signUp";
    }
}
