package com.dobby.petclinic.dao.config;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 这里的@MapperScan 导入的是Mapper 项目中的
 * tk.mybatis.spring.annotation.MapperScan;
 */
@MapperScan(basePackages = "com.dobby.petclinic.dao.mapper")
@Configuration
public class MyBatisConfig {


}