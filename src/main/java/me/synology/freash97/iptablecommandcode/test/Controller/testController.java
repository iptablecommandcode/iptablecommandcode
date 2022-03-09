package me.synology.freash97.iptablecommandcode.test.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Slf4j
@Controller
public class testController {

    @RequestMapping("")
    public String test_Index_Data(Model model){

        List<Object> noticelist = new ArrayList<>();
        Map<String,String> test = new HashMap<>();

        test.put("NOTICE_NO","1");
        test.put("ID","test");
        test.put("TITLE","제목테스트");
        test.put("DATE","2021-12-21");
        test.put("SUBTITLE","머릿말");
        log.debug("debugtest");
        log.info("infotest");
        log.warn("warntest");

        noticelist.add(test);

        model.addAttribute("noticelist",noticelist);
        model.addAttribute("start",2);
        model.addAttribute("end",4);
        model.addAttribute("maxpage",10);

        return "index";
    }

}
