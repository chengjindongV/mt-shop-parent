package com.mayikt.member.impl.service;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import com.mayikt.member.api.service.MemberOpenIdTokenLogin;
import com.mayikt.member.impl.entitydo.UnionLoginDo;
import com.mayikt.member.impl.entitydo.UserDo;
import com.mayikt.member.impl.mapper.UnionLoginMapper;
import com.mayikt.member.impl.strategy.UnionLoginStrategy;
import com.mayikt.utils.SpringContextUtils;
import com.mayikt.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: MemberOpenIdTokenLoginImpl
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/2422:02
 */
@RestController
public class MemberOpenIdTokenLoginImpl extends BaseApiService implements MemberOpenIdTokenLogin {
    @Autowired
    private TokenUtils tokenUtils;

    @Value("${mayikt.login.token.prefix}")
    private String loginTokenPrefix;
    @Autowired
    private UnionLoginMapper unionLoginMapper;

    @Override
    public BaseResponse<JSONObject> openIdLoginToken(String openToken) {
        if (StringUtils.isEmpty(openToken)) {
            return null;
        }
        // 1.根据openToken 获取真实openid
        String tokenValue = tokenUtils.getTokenValue(openToken);
        if (StringUtils.isEmpty(tokenValue)) {
            return setResultError("流程已经失效或者tpken错误");
        }
        // 2.根据openid 查询是否关联
        JSONObject jsonObject = JSONObject.parseObject(tokenValue);

        String unionPublicId = jsonObject.getString("unionPublicId");
        // 3.根据该渠道id查询bean的id ，从容器中获取
        // 根据渠道id查询 联合基本信息
        UnionLoginDo unionLoginDo = unionLoginMapper.selectByUnionLoginId(unionPublicId);
        if (unionLoginDo == null) {
            return setResultError("该渠道可能已经关闭或者不存在");
        }
        String unionBeanId = unionLoginDo.getUnionBeanId();
        UnionLoginStrategy unionLoginStrategy = SpringContextUtils.
                getBean(unionBeanId, UnionLoginStrategy.class);
        if (unionLoginStrategy == null) {
            return setResultError("没有查询到该策略");
        }
        String openid = jsonObject.getString("openid");
//        UserDo userDo = null;
////        switch (unionPublicId) {
////            case "mayikt_qq":
////                userDo = userMapper.selectByQQOpenId(openid);
////                break;
////            case "mayikt_weixin":
////                userDo = userMapper.selectByOpenId(openid);
////                break;
////        }
        UserDo userDo = unionLoginStrategy.getDbOpenId(openid);
        if (userDo == null) {
            return setResultError("当前用户没有关联，应该跳转到关联页面");
        }
        // 3.如果已经关联则，自动帮助实现登陆

        //获取userId
        Long userId = userDo.getUserId();
        String userToken = tokenUtils.createToken(loginTokenPrefix, userId + "");
        /**
         * data{
         *     "userToken":""
         * }
         */
        JSONObject data = new JSONObject();
        data.put("userToken", userToken);
        return setResultSuccess(data);
    }
}
