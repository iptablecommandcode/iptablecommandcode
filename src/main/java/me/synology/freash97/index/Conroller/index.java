package me.synology.freash97.index.Conroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class index {
    @GetMapping("index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("index");

        return modelAndView;
    }
}
