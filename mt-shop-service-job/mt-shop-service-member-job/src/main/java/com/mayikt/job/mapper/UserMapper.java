package com.mayikt.job.mapper;



import com.mayikt.job.entitydo.UserDo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


/**
 * @version V1.0
 * @description: 用户Mapper
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 * 私自分享视频和源码属于违法行为。
 */
public interface UserMapper {

    @Update("\n" +
            "update meite_user set WX_OPENID=#{wxOpenId}  where user_id=#{userId};")
    int updateUseOpenId(@Param("userId") Long userId, @Param("wxOpenId") String wxOpenId);

    @Select("SELECT USER_ID AS USERID ,MOBILE AS MOBILE ,password as password\n" +
            ",user_name as username ,user_name as username,sex as sex \n" +
            ",age as age ,create_time as createtime,IS_AVALIBLE as ISAVALIBLE\n" +
            ",\n" +
            "pic_img  as picimg,qq_openid as qqopenid ,wx_openid as wxopenid\n" +
            "\n" +
            "from meite_user where trim(WX_OPENID)!=''  limit #{index},#{pageSize}; ")
    List<UserDo> selectByOpenIdNotIsNull(@Param("index") Integer index, @Param("pageSize") Integer pageSize);


}
