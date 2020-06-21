package com.mayikt.member.impl.manage;

import com.mayikt.member.impl.entitydo.UserLoginLogDo;
import com.mayikt.member.impl.feign.WeChatLoginTemplateFeign;
import com.mayikt.member.impl.mapper.UserLoginLogMapper;
import com.mayikt.utils.DesensitizationUtil;
import com.mayikt.utils.TokenUtils;
import com.mayikt.weixin.api.request.LoginTemplateDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author chengjindong
 * @title: AsyncLoginLogManage
 */
@Component
@Slf4j
public class AsyncLoginLogManage {
    @Autowired
    private UserLoginLogMapper userLoginLogMapper;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private WeChatLoginTemplateFeign weChatLoginTemplateFeign;

    @Async
    public void loginLog(String openId, String mobile, Long userId, String loginIp, Date loginTime, String loginToken, String channel,
                         String equipment) {

//        UserLoginLogDo userLoginLogDo = new UserLoginLogDo(userId, loginIp, loginTime, loginToken, channel, equipment);
//        userLoginLogMapper.insertUserLoginLog(userLoginLogDo);
//        System.out.println(">>>>>线程名称:" + Thread.currentThread().getName() + ",userLoginLogDo:" + userLoginLogDo.toString() +
//                ",流程2");

//        System.out.println(">>>>>loginLog线程名称:" + Thread.currentThread().getName());
        // 1. 根据当前的渠道+userid+可用 查询 该用户是否已经登陆过
        UserLoginLogDo userLoginLogDo =
                userLoginLogMapper.selectByUserIdAndLoginType(userId, channel);
        // 2. 如果没有查询到记录情况下  如果能够查询呢
        if (userLoginLogDo != null) {
            // 原来token
            String oldToken = userLoginLogDo.getLoginToken();
            // 更新数据库token状态
            userLoginLogMapper.updateUserTokenNotQuipment(oldToken);
            // 从redis删除该token
            tokenUtils.delToken(oldToken);
        }
        // 插入最新的token到数据库中
        UserLoginLogDo newUserLoginLogDo = new UserLoginLogDo(userId, loginIp, loginTime, loginToken, channel, equipment);
        userLoginLogMapper.insertUserLoginLog(newUserLoginLogDo);

        // 3. 调用微信接口发送消息模版
        if (!StringUtils.isEmpty(openId)) {
            LoginTemplateDto loginTemplateDto = new
                    LoginTemplateDto(DesensitizationUtil.mobileEncrypt(mobile),
                    loginTime, loginIp, equipment, openId);
            weChatLoginTemplateFeign.sendLoginTemplate(loginTemplateDto);
        }

        // 4.关联我们的openid


    }
}
