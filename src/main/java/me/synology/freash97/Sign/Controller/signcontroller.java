package me.synology.freash97.Sign.Controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


@Slf4j
@Controller
@NoArgsConstructor
public class signcontroller {
    private final static String value = "Sign/";

    @GetMapping(value + "Sign_In")
    public ModelAndView sign_in(){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName(value + "Sign_In");

        return modelAndView;
    }

    @GetMapping(value + "Sign_Up")
    public ModelAndView sign_up(){

        ModelAndView modelAndView = new ModelAndView();


        return modelAndView;
    }
    /*Register의 Id 중복 확인용 */
    @PostMapping (value + "id_Chk")
    @ResponseBody
    public String id_chk(@RequestBody Map<String,Object> param, ModelAndView modelAndView){
        String test = (String) param.get("Id");
        log.info(test);
        return "success";
    }
    @PostMapping(value + "Register")
    public String register(@RequestBody Map<String,Object> param){
        log.debug("success");
        return "index";
    }
}