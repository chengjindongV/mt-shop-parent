package com.mayikt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @autho 程晋东
 * @title: AppGateway
 * @description: 每特教育独创微服务电商项目
 * @date 2020/4/1815:55
 */
@SpringBootApplication
public class AppGateway {
    /**
     * Gateway的底层是基于webfux
     * SpringMVC底层基于我们的servet实现
     * Spring-web封装了我们SpringMVC
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(AppGateway.class);
    }
}
