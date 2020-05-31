package com.mayikt.member.impl.mapper;


import com.mayikt.member.impl.entitydo.UserDo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


/**
 * @version V1.0
 * @description: 用户Mapper
 * @author: chengjindong
 */
public interface UserMapper {

    @Insert("INSERT INTO `meite_user` VALUES (null, #{mobile},null, #{passWord}, null, '0', '0', null," +
            " '1', now(), null, null);\n")
    int register(UserDo userDo);

    @Select("SELECT USER_ID AS USERID ,MOBILE AS MOBILE ,password as password\n" +
            ",user_name as username ,user_name as username,sex as sex \n" +
            ",age as age ,create_time as createtime,IS_AVALIBLE as ISAVALIBLE\n" +
            ",\n" +
            "pic_img  as picimg,qq_openid as qqopenid ,wx_openid as wxopenid\n" +
            "\n" +
            "from meite_user  where MOBILE=#{mobile}")
    UserDo login(String mobile, String passWord);

    @Select("SELECT USER_ID AS USERID ,MOBILE AS MOBILE ,password as password\n" +
            ",user_name as username ,user_name as username,sex as sex \n" +
            ",age as age ,create_time as createtime,IS_AVALIBLE as ISAVALIBLE\n" +
            ",\n" +
            "pic_img  as picimg,qq_openid as qqopenid ,wx_openid as wxopenid\n" +
            "\n" +
            "from meite_user  where MOBILE=#{mobile}")
    UserDo existMobile(String mobile);

    @Select("SELECT USER_ID AS USERID ,MOBILE AS MOBILE ,password as password\n" +
            ",user_name as username ,user_name as username,sex as sex \n" +
            ",age as age ,create_time as createtime,IS_AVALIBLE as ISAVALIBLE\n" +
            ",\n" +
            "pic_img  as picimg,qq_openid as qqopenid ,wx_openid as wxopenid\n" +
            "\n" +
            "from meite_user  where USER_ID=#{userId}")
    UserDo findByUser(Long userId);

    @Update("\n" +
            "update meite_user set WX_OPENID=#{wxOpenId}  where user_id=#{userId};")
    int updateUseOpenId(@Param("userId") Long userId, @Param("wxOpenId") String wxOpenId);

    @Select("SELECT USER_ID AS USERID ,MOBILE AS MOBILE ,password as password\n" +
            ",user_name as username ,user_name as username,sex as sex \n" +
            ",age as age ,create_time as createtime,IS_AVALIBLE as ISAVALIBLE\n" +
            ",\n" +
            "pic_img  as picimg,qq_openid as qqopenid ,wx_openid as wxopenid\n" +
            "\n" +
            "from meite_user  where wx_OpenId=#{wxOpenId}")
    UserDo selectByOpenId(@Param("wxOpenId") String wxOpenId);


    @Update("\n" +
            "update meite_user set WX_OPENID=null  where WX_OPENID=#{wxOpenId};")
    int cancelFollowOpenId(@Param("wxOpenId") String wxOpenId);

}
