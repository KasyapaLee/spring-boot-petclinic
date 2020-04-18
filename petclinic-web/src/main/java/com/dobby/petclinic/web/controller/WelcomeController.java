package com.dobby.petclinic.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author liguoqing
 * @date 2019-01-17
 */
@Controller
public class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }
}
