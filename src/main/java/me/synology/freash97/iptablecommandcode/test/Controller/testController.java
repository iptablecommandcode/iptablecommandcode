package me.synology.freash97.iptablecommandcode.test.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class testController {

    @RequestMapping("/admin")
    public String test_Index_Data(ModelAndView modelAndView){
        log.debug("어드민 페이지 확인");
        return "/Auth/index_admin";
    }

}
