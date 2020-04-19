package com.dobby.petclinic.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 启动的时候，默认扫描的是启动类所在包路径下的bean
 * 这里指定扫描的基本包路径，让 Spring Boot 可以扫描到 petclinic-service 模块中的bean
 */
@SpringBootApplication(scanBasePackages = "com.dobby.petclinic")
public class PetclinicWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetclinicWebApplication.class, args);
    }

}
