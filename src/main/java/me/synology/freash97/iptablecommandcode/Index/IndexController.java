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
    public ModelAndView index(ModelAndView modelAndView){

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
        modelAndView.addObject("account","test");
        modelAndView.addObject("notice_info","notice");

        modelAndView.setViewName("index");

        log.debug("model 기본 정보 입력" + modelAndView);

        return modelAndView;
    }
    @RequestMapping("admin")
    public ModelAndView admin(ModelAndView modelAndView){
        List<Object> adminlist = new ArrayList<>();
        Map<String,String> admin = new HashMap<>();

        for (int i = 1; i < 3; i++){
            admin.put("ENTR_NO", "" + i);
            admin.put("ID", "test" + i);
            admin.put("NAME", "제목테스트" + i);
            admin.put("DATE", "2021-12-2" + i);
            admin.put("ADMIN_CHECK", "관리자" + i);

            log.debug("ACCOUNT 기본 정보 입력 -> " + String.valueOf(admin));
            adminlist.add(admin);
        }

        log.debug("입력받은 내용 리스트" + adminlist);
        modelAndView.addObject("adminlist",adminlist);

        modelAndView.addObject("notice_info","admin");

        modelAndView.setViewName("index");

        log.debug("model 기본 정보 입력" + modelAndView);

        return modelAndView;
    }
}
