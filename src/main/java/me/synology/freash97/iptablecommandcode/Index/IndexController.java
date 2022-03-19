package me.synology.freash97.iptablecommandcode.Index;

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
public class IndexController {
    @RequestMapping("")
    public ModelAndView test_Index_Data(ModelAndView modelAndView){

        List<Object> noticelist = new ArrayList<>();
        Map<String,String> notice = new HashMap<>();

        notice.put("NOTICE_NO", "1");
        notice.put("ID", "test");
        notice.put("TITLE", "제목테스트");
        notice.put("DATE", "2021-12-21");
        notice.put("SUBTITLE", "머릿말");

        log.debug("notice 기본 정보 입력 -> " + String.valueOf(notice));

        noticelist.add(notice);

        modelAndView.addObject("noticelist",noticelist);
        modelAndView.addObject("start",2);
        modelAndView.addObject("end",4);
        modelAndView.addObject("maxpage",10);

        modelAndView.setViewName("index");

        log.debug("model 기본 정보 입력" + modelAndView);

        return modelAndView;
    }
}
