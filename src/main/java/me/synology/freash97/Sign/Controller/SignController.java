package me.synology.freash97.Sign.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignController {
    private final String value = "Sign/";
    @GetMapping(value + "Sign_In")
    public ModelAndView Sign_In(){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName(value + "Sign_In");

        return modelAndView;
    }

    @GetMapping(value + "Sign_Up")
    public ModelAndView Sign_Up(){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName(value + "Sign_Up");

        return modelAndView;
    }
}
