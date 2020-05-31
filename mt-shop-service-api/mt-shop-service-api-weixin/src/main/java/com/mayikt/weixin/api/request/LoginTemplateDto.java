package com.mayikt.weixin.api.request;

import lombok.Data;

import java.util.Date;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: LoginTemplateDto
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/1717:46
 */
@Data
public class LoginTemplateDto {
    private String phone;
    private Date loginTime;
    private String loginIp;
    private String equipment;

    public LoginTemplateDto(String phone, Date loginTime, String loginIp, String equipment, String openId) {
        this.phone = phone;
        this.loginTime = loginTime;
        this.loginIp = loginIp;
        this.equipment = equipment;
        this.openId = openId;
    }

    /**
     * openid
     */
    private String openId;
}
