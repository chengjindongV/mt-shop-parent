package com.mayikt.member.api.dto.resp;

import lombok.Data;

/**
 * @author chengjindong
 * @title: UserRespDto
 */
@Data
public class UserRespDto {
  
    /**
     * 手机号码
     */

    private String mobile;
    /**
     * 邮箱
     */

    private String email;

    /**
     * 用户名称
     */

    private String userName;
    /**
     * 性别 0 男 1女
     */

    private char sex;
    /**
     * 年龄
     */

    private Long age;


    /**
     * 用户头像
     */

    private String picImg;


}
