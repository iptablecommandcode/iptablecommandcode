package me.synology.freash97.iptablecommandcode.Auth.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class SignController {

    public static final String urlVal = "/Sign";

//    로그인페이지
    @RequestMapping(urlVal + "/Sign_In")
    public ModelAndView Sign_In(ModelAndView modelAndView){
        log.debug("Sign_In 페이지 접속");

        modelAndView.addObject("Sign_info", "Sign_In");

        modelAndView.setViewName(urlVal + "/Sign");

        return modelAndView;
    }

//    로그인 시도시
    @PostMapping(urlVal + "/Sign_In.do")
    public String Sign_in(@RequestParam Map<String, Object> param, Model model){

        System.out.println(param);

        List<Object> noticelist = new ArrayList<>();
        Map<String,String> test = new HashMap<>();

        test.put("NOTICE_NO","1");
        test.put("ID","test");
        test.put("TITLE","제목테스트");
        test.put("DATE","2021-12-21");
        test.put("SUBTITLE","머릿말");

        noticelist.add(test);

        model.addAttribute("noticelist",noticelist);
        model.addAttribute("start",1);
        model.addAttribute("end",1);
        model.addAttribute("maxpage",1);

        return "index";
    }

    @RequestMapping(urlVal + "/Sign_Up")
    public ModelAndView Sign_Up(ModelAndView modelAndView){
        log.debug("Sign_Up 페이지 접속");

        modelAndView.addObject("Sign_info", "Sign_Up");

        modelAndView.setViewName(urlVal + "/Sign");

        return modelAndView;
    }

    @PostMapping(urlVal + "/Sign_Up.do")
    public void Sign_Up(@RequestParam Map<String, Object> param){
        System.out.println(param);
    }
}