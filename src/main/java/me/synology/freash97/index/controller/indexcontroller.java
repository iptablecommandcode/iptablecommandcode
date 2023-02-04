package me.synology.freash97.index.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
@Slf4j
@NoArgsConstructor
public class indexcontroller {
    @GetMapping("/")
    public String index(){
        Integer a = 3123;

        log.info(a.toString());


        return "index";
    }
}