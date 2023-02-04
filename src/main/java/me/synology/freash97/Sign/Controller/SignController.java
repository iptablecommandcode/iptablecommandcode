package me.synology.freash97.Sign.Controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import me.synology.freash97.Sign.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


@Slf4j
@Controller
@NoArgsConstructor
public class SignController {
    private final String value = "Sign/";

    private UserService userService;

    @GetMapping(value + "Sign_In")
    public ModelAndView Sign_In(){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName(value + "Sign_In");

        return modelAndView;
    }

    @GetMapping(value + "Sign_Up")
    public ModelAndView Sign_Up() throws Exception{

        ModelAndView modelAndView = new ModelAndView();


        return modelAndView;
    }
    /*Register의 Id 중복 확인용 */
    @PostMapping (value + "id_Chk")
    @ResponseBody
    public String Sign_Up(@RequestBody Map<String,Object> param, ModelAndView modelAndView){
        String test = (String) param.get("Id");
        log.info(test);
        return "success";
    }
    @PostMapping(value + "Register")
    public String Register(@RequestBody Map<String,Object> param){
        log.debug("success");
        return "index";
    }
}