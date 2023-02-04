package me.synology.freash97.sign.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


@Slf4j
@Controller
@NoArgsConstructor
public class SIGNCONTROLLER {
    private static final String VALUE = "Sign/";

    @GetMapping(VALUE + "Sign_In")
    public ModelAndView signIn(){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName(VALUE + "Sign_In");

        return modelAndView;
    }

    @GetMapping(VALUE + "Sign_Up")
    public String signUp(){
        return VALUE + "Sign_Up";
    }
    /*Register의 Id 중복 확인용 */
    @PostMapping (VALUE + "id_Chk")
    @ResponseBody
    public String idChk(@RequestBody Map<String,Object> param, ModelAndView modelAndView){
        String test = (String) param.get("Id");
        log.info(test);
        return "success";
    }
    @PostMapping(VALUE + "Register")
    public String register(@RequestBody Map<String,Object> param){
        log.debug("success");
        return "index";
    }
}