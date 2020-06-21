package com.mayikt.member.impl.strategy.impl;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.http.HttpClientUtils;
import com.mayikt.member.impl.entitydo.UnionLoginDo;
import com.mayikt.member.impl.entitydo.UserDo;
import com.mayikt.member.impl.mapper.UserMapper;
import com.mayikt.member.impl.strategy.UnionLoginStrategy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: WeiXinUnionLoginStrategy
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/2421:32
 */
@Component
public class WeiXinUnionLoginStrategy implements UnionLoginStrategy {
    @Value("${mayikt.login.wx.accesstoken}")
    private String weixinAccessTokenAddres;
    @Autowired
    private UserMapper userMapper;

    @Override
    public String unionLoginCallback(HttpServletRequest request, UnionLoginDo unionLoginDo) {
        String code = request.getParameter("code");
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        // 1.根据授权码获取accessToken 和openid
        // 获取微信newWeixinAccessTokenAddres
        String newWeixinAccessTokenAddres = weixinAccessTokenAddres.replace("APPID", unionLoginDo.getAppId() + "").replace("SECRET",
                unionLoginDo.getAppKey()).replace("CODE", code);
        JSONObject accessTokenResult = HttpClientUtils.httpGet(newWeixinAccessTokenAddres);
        if (accessTokenResult == null) {
            return null;
        }
        boolean errcode = accessTokenResult.containsKey("errcode");
        if (errcode) {
            return null;
        }
        // 获取openid
        String openid = accessTokenResult.getString("openid");
        if (StringUtils.isEmpty(openid)) {
            return null;
        }
        return openid;
    }

    @Override
    public UserDo getDbOpenId(String openId) {
        return userMapper.selectByOpenId(openId);
    }


}
