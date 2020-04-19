package com.mayikt.member.api.service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 程晋东
 * @title: MemberService
 * @description: 每特教育独创微服务电商项目
 * @date 2020/4/1815:55
 */
@Api(tags = "会员基本服务接口")
public interface MemberService {
    /**
     * 会员服务调用微信接口
     *
     * @param userId
     * @return
     */
    @GetMapping("/memberToWeiXin")
    @ApiOperation("会员调用微信接口接口")
    @ApiImplicitParam(name = "userId", value = "用户的id", required = true)
    @ApiResponse(code = 200, message = "响应成功")
    String memberToWeiXin(Long userId);
}
