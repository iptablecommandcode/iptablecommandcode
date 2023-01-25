package me.synology.freash97.Sign.Controller;

import lombok.extern.slf4j.Slf4j;

import me.synology.freash97.Sign.Entity.UserEntity;
import me.synology.freash97.Sign.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@Slf4j
public class SignController {
    private final String value = "Sign/";

    private  final UserService userService;

    SignController(UserService userService){
        this.userService = userService;
    }
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
}
