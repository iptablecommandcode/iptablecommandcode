package me.synology.freash97.iptablecommandcode.test.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class testController {

    @RequestMapping("/admin")
    public ModelAndView test_Index_Data(ModelAndView modelAndView){

        List<Object> adminlist = new ArrayList<>();
        Map<String,String> admin = new HashMap<>();

        admin.put("ENTR_NO", "1");
        admin.put("ID", "test");
        admin.put("NAME", "제목테스트");
        admin.put("DATE", "2021-12-21");
        admin.put("ADMIN_CHECK", "관리자");

        log.debug("ACCOUNT 기본 정보 입력 -> " + String.valueOf(admin));

        adminlist.add(admin);

        modelAndView.addObject("adminlist",adminlist);
        modelAndView.addObject("start",2);
        modelAndView.addObject("end",4);
        modelAndView.addObject("maxpage",10);

        modelAndView.setViewName("/Auth/index_admin");

        log.debug("model 기본 정보 입력" + modelAndView);

        return modelAndView;
    }
}
