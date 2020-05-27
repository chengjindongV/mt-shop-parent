package com.mayikt;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: chengjindong
 * @Date: 2020/4/16 23:05
 */
@SpringBootApplication
@EnableSwagger2Doc
@MapperScan("com.mayikt.weixin.impl.mapper")
public class AppWeiXin {
    public static void main(String[] args) {
        SpringApplication.run(AppWeiXin.class);
    }
}
