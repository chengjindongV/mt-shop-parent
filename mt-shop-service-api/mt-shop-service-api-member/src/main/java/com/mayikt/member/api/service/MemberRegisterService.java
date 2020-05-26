package com.mayikt.member.api.service;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.base.BaseResponse;
import com.mayikt.member.api.dto.req.UserReqRegisterDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author chengjindong
 * @title: MemberRegisterService
 */
@Api(tags = "会员注册服务接口")
public interface MemberRegisterService {

    /**
     * 会员注册接口
     *
     * @param userReqRegisterDto
     * @return
     */
//    @GetMapping("/register")
    @PostMapping("/register")
    BaseResponse<JSONObject> register(@RequestBody UserReqRegisterDto userReqRegisterDto);

    /**
     * rpc http+json
     * register?userName=111&pwd=11111
     */
}
