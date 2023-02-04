package me.synology.freash97.index.Conroller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@NoArgsConstructor
class IndexController {
    @GetMapping("/")
    public String index(){
        Integer a = 3123;

        log.info(a.toString());


        return "index";
    }
}
