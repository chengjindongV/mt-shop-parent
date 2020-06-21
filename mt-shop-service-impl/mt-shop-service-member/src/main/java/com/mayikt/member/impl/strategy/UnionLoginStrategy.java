package com.mayikt.member.impl.strategy;

import com.mayikt.member.impl.entitydo.UnionLoginDo;
import com.mayikt.member.impl.entitydo.UserDo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: UnionLoginStrategy
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/2122:16
 */
public interface UnionLoginStrategy {

    String unionLoginCallback(HttpServletRequest request, UnionLoginDo unionLoginDo);

    UserDo getDbOpenId(String openId);
}
