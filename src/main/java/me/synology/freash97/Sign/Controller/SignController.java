package me.synology.freash97.Sign.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignController {
    private final String value = "/Sign/";
    @GetMapping(value + "Sign_In")
    public String Sign_In(){
        return "Sign/Sign_In";
    }
}
