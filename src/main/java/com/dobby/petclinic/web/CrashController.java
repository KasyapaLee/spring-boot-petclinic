package com.dobby.petclinic.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author liguoqing
 * @date 2019-01-17
 */
@Controller
public class CrashController {


    @GetMapping("/oups")
    public String triggerException() {
        throw new RuntimeException("出错了！Expected: controller used to showcase what happens when an exception is thrown");
    }
}
