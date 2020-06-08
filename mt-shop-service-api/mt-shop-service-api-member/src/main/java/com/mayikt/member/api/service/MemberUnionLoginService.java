package com.mayikt.member.api.service;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.base.BaseResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "联合登陆接口")
public interface MemberUnionLoginService {

    /**
     * 根据不同的联合登陆id
     *
     * @param unionPublicId
     * @return
     */
    @GetMapping("/unionLogin")
    BaseResponse<String> unionLogin(@RequestParam("unionPublicId") String unionPublicId);

    /**
     * 联合登陆回调接口
     *
     * @return
     */
    @GetMapping("/login/oauth/callback")
    BaseResponse<JSONObject> unionLoginCallback(@RequestParam("unionPublicId") String unionPublicId);


}
