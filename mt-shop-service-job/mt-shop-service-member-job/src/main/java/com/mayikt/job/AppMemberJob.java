package com.mayikt.job;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: AppMemberJob
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/1921:41
 */
@SpringBootApplication
@MapperScan("com.mayikt.job.mapper")
public class AppMemberJob {
    public static void main(String[] args) {
        SpringApplication.run(AppMemberJob.class);
    }
}
