package com.mayikt.member.api.service;

import com.mayikt.base.BaseResponse;
import com.mayikt.member.api.dto.resp.UserRespDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    /**
     * 关联用户的openid
     *
     * @param userId
     * @param openId
     * @return
     */
    @PostMapping("/updateUseOpenId")
    @ApiOperation("关联用户的openid")
    BaseResponse<Object> updateUseOpenId(@RequestParam("userId") Long userId,
                                         @RequestParam(name = "openId", required = false) String openId);

    /**
     * 根据openid  查询用户信息
     *
     * @param openId
     * @return
     */
    @GetMapping("/selectByOpenId")
    @ApiOperation("根据openid 查询用户信息")
    BaseResponse<UserRespDto> selectByOpenId(
            @RequestParam("openId") String openId);


    /**
     * 取消关注
     *
     * @param openId
     * @return
     */
    @GetMapping("/cancelFollowOpenId")
    BaseResponse<Object> cancelFollowOpenId(@RequestParam("openId") String openId);
}
