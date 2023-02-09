package me.synology.freash97.sign.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import me.synology.freash97.member.entity.MemberEntity;
import me.synology.freash97.sign.service.SignService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Slf4j
@Controller
@AllArgsConstructor
public class SignController {
    public SignService signService;
    private static final String VALUE = "Sign/";

    @GetMapping(VALUE + "Sign_In")
    public String signIn(){
        List<MemberEntity> test = signService.selectMember();
        log.debug("Sign_In");
        log.debug(test.toString());
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