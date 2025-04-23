package me.synology.freash97.index.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName   : me.synology.freash97.index.controller
 * fileName      : indexController
 * author        : iptab
 * date          : 2025-04-23
 * time          : 오후 10:53
 * description   :
 * ====================================================
 * DATE                  AUTHOR              NOTE
 * ----------------------------------------------------
 * 2025-04-23               iptab             최초 생성
 */
@Slf4j
@Controller
public class indexController {

    @GetMapping("/")
    public String main(){
        log.debug("main Controller Start !!!");

        return "index";
    }

    @GetMapping("/index")
    public String index(){
        log.debug("index Controller Start !!!");

        return "index";
    }

}
