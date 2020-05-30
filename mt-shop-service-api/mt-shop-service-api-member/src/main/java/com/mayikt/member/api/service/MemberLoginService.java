package com.mayikt.member.api.service;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.base.BaseResponse;
import com.mayikt.member.api.dto.req.UserLoginDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author chengjindong
 * @title: MemberLoginService
 */
@Api(tags = "会员登陆接口")
public interface MemberLoginService {

    @PostMapping("/login")
    BaseResponse<JSONObject> login(@RequestBody UserLoginDto userLoginDto, @RequestHeader("X-Real-IP")
            String sourceIp, @RequestHeader("channel") String channel,
                                   @RequestHeader("deviceInfor") String deviceInfor);
}
