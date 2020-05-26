package com.mayikt.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: TokenUtils
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/1221:47
 */
@Component
public class TokenUtils {
    @Autowired
    private RedisUtil redisUtil;

    public String createToken(String prefix
            , String value) {
        return createToken(prefix, value, null);
    }

    /**
     * 前缀
     *
     * @param prefix
     * @param value
     * @return
     */
    public String createToken(String prefix
            , String value, Long timeOut) {
        //1.生成我们的令牌
        String token = prefix + "_" + UUID.randomUUID().toString().replace("-", "");
        // 2.将该token存入到Redis中
        redisUtil.setString(token, value, timeOut);
        return token;
    }

    public String getTokenValue(String token) {
        return redisUtil.getString(token);
    }

}
