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
        modelAndView.addObject("start",2);
        modelAndView.addObject("end",4);
        modelAndView.addObject("maxpage",10);

        modelAndView.setViewName("/Auth/index_admin");

        log.debug("model 기본 정보 입력" + modelAndView);

        return modelAndView;
    }
}
