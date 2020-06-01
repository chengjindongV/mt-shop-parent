package com.mayikt.job.entitydo;

import lombok.Data;

import java.util.Date;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: UserEntity
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/1220:46
 */
@Data
public class UserDo {

    /**
     * userid
     */

    private Long userId;
    /**
     * 手机号码
     */

    private String mobile;
    /**
     * 邮箱
     */

    private String email;
    /**
     * 密码
     */

    private String passWord;
    /**
     * 用户名称
     */

    private String userName;
    /**
     * 性别 0 男 1女
     */

    private char sex;
    /**
     * 年龄
     */

    private Long age;
    /**
     * 注册时间
     */

    private Date createTime;
    /**
     * 修改时间
     */

    private Date updateTime;
    /**
     * 账号是否可以用 1 正常 0冻结
     */

    private char isAvalible;
    /**
     * 用户头像
     */

    private String picImg;
    /**
     * 用户关联 QQ 开放ID
     */

    private String qqOpenId;
    /**
     * 用户关联 微信 开放ID
     */
    private String wxOpenId;


}
