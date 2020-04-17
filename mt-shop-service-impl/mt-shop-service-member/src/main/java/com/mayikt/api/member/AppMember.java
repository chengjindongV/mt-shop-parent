package com.mayikt.api.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: chengjindong
 * @Date: 2020/4/17 22:20
 */
@SpringBootApplication
@EnableFeignClients
public class AppMember {
    public static void main(String[] args) {
        SpringApplication.run(AppMember.class);
    }
}
