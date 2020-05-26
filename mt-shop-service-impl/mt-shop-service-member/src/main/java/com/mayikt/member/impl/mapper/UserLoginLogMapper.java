package com.mayikt.member.impl.mapper;


import com.mayikt.member.impl.entitydo.UserLoginLogDo;
import org.apache.ibatis.annotations.Insert;

/**
 * @author chengjindong
 * @title: UserLoginLogMapper
 * @description: 每特教育独创微服务电商项目
 */
public interface UserLoginLogMapper {


    @Insert("\n" +
            "insert into  user_login_log values(null,#{userId},#{loginIp},now(),#{loginToken},#{channel},#{equipment});\n")
    int insertUserLoginLog(UserLoginLogDo userLoginLogDo);
}
