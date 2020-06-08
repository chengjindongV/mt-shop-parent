package com.mayikt.member.impl.strategy.impl;

import com.mayikt.http.HttpClientUtils;
import com.mayikt.member.impl.entitydo.UnionLoginDo;
import com.mayikt.member.impl.strategy.UnionLoginStrategy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: QQUnionLoginStrategy
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/2122:17
 */
@Component
public class QQUnionLoginStrategy implements UnionLoginStrategy {
    @Value("${mayikt.login.qq.accesstoken}")
    private String qqAccessTokenAddres;
    @Value("${mayikt.login.qq.openid}")
    private String qqOpenIdAddres;

    @Override
    public String unionLoginCallback(HttpServletRequest request, UnionLoginDo unionLoginDo) {
        String code = request.getParameter("code");
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        //1.根据授权码获取accessToken
        // 1.根据授权码获取accessToken
        qqAccessTokenAddres = qqAccessTokenAddres.replace("{client_id}"
                , unionLoginDo.getAppId()).replace("{client_secret}", unionLoginDo.getAppKey()).
                replace("{code}", code).replace("{redirect_uri}", unionLoginDo.getRedirectUri());
        String resultAccessToken = HttpClientUtils.httpGetResultString(qqAccessTokenAddres);
        boolean contains = resultAccessToken.contains("access_token=");
        if (!contains) {
            return null;
        }
        String[] split = resultAccessToken.split("=");
        String accessToken = split[1];
        if (StringUtils.isEmpty(accessToken)) {
            return null;
        }
        // 2.根据accessToken获取用户的openid
        String resultQQOpenId = HttpClientUtils.httpGetResultString(qqOpenIdAddres + accessToken);
        if (StringUtils.isEmpty(resultQQOpenId)) {
            return null;
        }
        boolean openid = resultQQOpenId.contains("openid");
        if (!openid) {
            return null;
        }
        String array[] = resultQQOpenId.replace("callback( {", "").
                replace("} );", "").split(":");
        String openId = array[2];
        return openId;
    }


}
