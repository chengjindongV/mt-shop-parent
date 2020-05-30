package com.mayikt.member.impl.mapper;


import com.mayikt.member.impl.entitydo.UserLoginLogDo;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author chengjindong
 * @title: UserLoginLogMapper
 * @description: 每特教育独创微服务电商项目
 */
public interface UserLoginLogMapper {


    @Insert("\n" +
            "insert into  user_login_log values(null,#{userId},#{loginIp},now(),#{loginToken},#{channel},#{equipment},1);\n")
    int insertUserLoginLog(UserLoginLogDo userLoginLogDo);

    @Select("\n" +
            "SELECT ID AS ID,USER_ID AS USERID,\n" +
            "login_time AS logintime,login_token AS logintoken\n" +
            ",channel AS channel,equipment AS equipment\n" +
            ",is_availability AS is_availability\n" +
            "FROM user_login_log WHERE channel =#{channel} and user_id=#{userId}\n " +
            "  and is_availability=1;")
    UserLoginLogDo selectByUserIdAndLoginType(@Param("userId") Long userId, @Param("channel") String channel);

    @Update("update user_login_log set is_availability=0 where login_token=#{loginToken};")
    int updateUserTokenNotQuipment(@Param("loginToken") String loginToken);
}
