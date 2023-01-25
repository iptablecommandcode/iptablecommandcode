package me.synology.freash97.Notice.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NoticeController {
    private final String value = "Notice/";

    @GetMapping(value + "Notice_Write")
    public ModelAndView Notice_write(){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName(value + "Notice_Write");

        return modelAndView;
    }

    @GetMapping(value + "Notice_View")
    public ModelAndView Notice_View(){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName(value + "Notice_View");

        return modelAndView;
    }
}
