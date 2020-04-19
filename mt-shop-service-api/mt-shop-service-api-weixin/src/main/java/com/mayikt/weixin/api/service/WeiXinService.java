package com.mayikt.weixin.api.service;

import com.mayikt.base.BaseResponse;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: chengjindong
 * @Date: 2020/4/16 22:34
 */
@Api(tags = "微信基本服务接口")
public interface WeiXinService {


    /**
     * 微信接口
     * @return
     */
    @GetMapping("/appInfo")
    @ApiOperation("appInfo接口")
    @ApiImplicitParam(name = "userId", value = "用户的id", required = true)
    @ApiResponse(code = 200, message = "响应成功")
    String appInfo(@RequestParam("userId")Long userId);

    /**
     * 添加我们的应用
     * @param appId
     * @param appPwd
     * @return
     */
    @GetMapping("/addApp")
    @ApiOperation("appInfo接口")
    @ApiImplicitParams({@ApiImplicitParam(name = "appId", value = "用户的appId", required = true),
            @ApiImplicitParam(name = "appPwd", value = "用户的appPwd", required = true)})
    @ApiResponses({
            @ApiResponse(code = 200, message = "响应成功"),
            @ApiResponse(code = 500, message = "系统错误")
    })
    BaseResponse<String> addApp(@RequestParam("appId") String appId,
                                @RequestParam("appPwd") String appPwd);
}
