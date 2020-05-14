package com.mayikt.weixin.impl.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author chengjindong
 * @description:
 * @date 2020/5/1414:47
 */
@Data
public class WechatKeyword {
    private Long id;
    private String keywordName;
    private String keywordValue;
    private Date createTime;
    private Date updateTime;
    private Long version;
}
