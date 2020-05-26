package com.mayikt.member.api.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chengjindong
 * @title: UserLoginRegisterDto
 */
@Data
public class UserLoginDto {
    /**
     * 手机号码
     * 密码
     * 短信验证码
     */
    /**
     * 手机号码
     */

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码", name = "mobile", required = true)
    private String mobile;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "passWord", required = true)
    private String passWord;
//
//    private String smsCode;

}
