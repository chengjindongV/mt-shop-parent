package com.mayikt.member.impl.service;

import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import com.mayikt.member.api.dto.resp.UserRespDto;
import com.mayikt.member.api.service.MemberInfoService;
import com.mayikt.member.impl.entitydo.UserDo;
import com.mayikt.member.impl.mapper.UserMapper;
import com.mayikt.utils.DesensitizationUtil;
import com.mayikt.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: MemberInfoServiceImpl
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/1222:27
 */
@RestController
public class MemberInfoServiceImpl extends BaseApiService implements MemberInfoService {
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseResponse<UserRespDto> getTokenUser(String token) {
        if (StringUtils.isEmpty(token)) {
            return setResultError("token不能问哦空");
        }
        // 从Redis中获取到UserID
        String redisValue = tokenUtils.getTokenValue(token);
        if (StringUtils.isEmpty(redisValue)) {
            return setResultError("token已经过期");
        }
        Long userId = Long.parseLong(redisValue);
        // 在根据userId查询用户的信息
        UserDo userDo = userMapper.findByUser(userId);
        if (userDo == null) {
            return setResultError("token已经过期或者错误!");
        }
        // 需要将do转换成dto
        UserRespDto userRespDto = doToDto(userDo, UserRespDto.class);
        String mobile = userRespDto.getMobile();
        userRespDto.setMobile(DesensitizationUtil.mobileEncrypt(mobile));
        return setResultSuccess(userRespDto);
    }

    @Override
    public BaseResponse<Object> updateUseOpenId(Long userId, String openId) {
        int reuslt = userMapper.updateUseOpenId(userId, openId);
        return setResultDb(reuslt, "关联成功", "关联失败");
    }

    @Override
    public BaseResponse<UserRespDto> selectByOpenId(String openId) {
        UserDo userDo = userMapper.selectByOpenId(openId);
        if (userDo == null) {
            return setResultError("根据openId查询该用户没有关注过");
        }
        // 需要将do转换成dto
        UserRespDto userRespDto = doToDto(userDo, UserRespDto.class);
        String mobile = userRespDto.getMobile();
        userRespDto.setMobile(DesensitizationUtil.mobileEncrypt(mobile));
        return setResultSuccess(userRespDto);
    }

    @Override
    public BaseResponse<Object> cancelFollowOpenId(String openId) {
        if (StringUtils.isEmpty(openId)) {
            return setResultError("openId不能为空");
        }
        UserDo userDo = userMapper.selectByOpenId(openId);
        if (userDo == null) {
            return setResultError("根据openId查询该用户没有关注过");
        }
        // 已经关注过，则将对应的用户的openid 变为空
        int result = userMapper.cancelFollowOpenId(openId);
        return setResultDb(result, "取消关注成功", "取消关注成功失败!");
    }

}


