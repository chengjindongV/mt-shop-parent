package com.mayikt.member.impl.entitydo;

import lombok.Data;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: UnionLoginDo
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/2115:59
 */
@Data
public class UnionLoginDo {
    Long id;
    /**
     * 登陆名称 比如 腾讯QQ 腾讯支付
     */
    String unionName;
    /**
     * appId
     */
    String appId;
    /**
     * 联合登陆的id
     */
    String unionPublicId;
    /**
     * beanId
     */
    String unionBeanId;
    /**
     * appKey
     */
    String appKey;
    /**
     * redirectUri 回调地址
     */
    String redirectUri;
    /**
     * 回调地址
     */
    String requestAddress;
}
