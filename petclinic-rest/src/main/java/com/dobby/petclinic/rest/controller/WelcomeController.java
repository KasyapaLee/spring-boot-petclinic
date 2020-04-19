package com.dobby.petclinic.rest.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liguoqing
 * @date 2019-01-17
 */
@RestController
public class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }
}
