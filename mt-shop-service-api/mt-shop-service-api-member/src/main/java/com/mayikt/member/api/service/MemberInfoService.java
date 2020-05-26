package com.mayikt.member.api.service;

import com.mayikt.base.BaseResponse;
import com.mayikt.member.api.dto.resp.UserRespDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chengjindong
 * @title: MemberInfoService
 */
@Api(tags = "会员服务基本信息接口")
public interface MemberInfoService {

    /**
     * 根据用户token查询用户的信息
     *
     * @param token
     * @return
     */
    @GetMapping("/getTokenUser")
    @ApiOperation("根据token查看用户信息")
    @ApiImplicitParam(name = "token", value = "token", required = true)
    BaseResponse<UserRespDto> getTokenUser(@RequestParam("token") String token);
}
