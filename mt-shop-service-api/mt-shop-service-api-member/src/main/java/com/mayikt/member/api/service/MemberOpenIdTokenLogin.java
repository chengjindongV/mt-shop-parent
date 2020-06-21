package com.mayikt.member.api.service;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.base.BaseResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "基于OpenIdToken登陆")
public interface MemberOpenIdTokenLogin {

    @GetMapping("/openIdToken")
    BaseResponse<JSONObject> openIdLoginToken(@RequestParam("openIdToken") String openToken);
}
