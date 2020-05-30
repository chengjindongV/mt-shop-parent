package com.mayikt;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author: chengjindong
 * @Date: 2020/4/17 22:20
 */
@EnableFeignClients
@SpringBootApplication
@EnableSwagger2Doc
@MapperScan("com.mayikt.member.impl.mapper")
@EnableAsync
public class AppMember {
    public static void main(String[] args) {
        SpringApplication.run(AppMember.class);
    }
}
