package com.mayikt.member;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: chengjindong
 * @Date: 2020/4/17 22:20
 */
@SpringBootApplication
@EnableFeignClients
@EnableSwagger2Doc
public class AppMember {
    public static void main(String[] args) {
        SpringApplication.run(AppMember.class);
    }
}
