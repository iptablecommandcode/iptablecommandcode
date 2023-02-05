package me.synology.freash97.sign.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


@Slf4j
@Controller
@NoArgsConstructor
public class SignController {
    private static final String VALUE = "Sign/";

    @GetMapping(VALUE + "Sign_In")
    public String signIn(){
        log.debug("Sign_In");
        return VALUE + "Sign_In";
    }

    @GetMapping(VALUE + "Sign_Up")
    public String signUp(){
        log.debug("Sign_Up");
        return VALUE + "Sign_Up";
    }
    /*Register의 Id 중복 확인용 */
    @PostMapping (VALUE + "id_Chk")
    @ResponseBody
    public String idChk(@RequestBody Map<String,Object> param){
        String test = (String) param.get("Id");
        log.debug(test);
        return "success";
    }
    @PostMapping(VALUE + "Register")
    public String register(@RequestParam Map<String,Object> param){
        log.debug("success");
        return "redirect:Sign_In";
    }
    @PostMapping(VALUE + "Sign_In.do")
    public String signInAction(@RequestParam Map<String, Object> param){
        log.debug("Sign_In.do");
        return "index";
    }
}