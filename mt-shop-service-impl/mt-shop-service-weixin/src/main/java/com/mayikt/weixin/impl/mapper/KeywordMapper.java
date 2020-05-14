package com.mayikt.weixin.impl.mapper;


import com.mayikt.weixin.impl.entity.WechatKeyword;
import org.apache.ibatis.annotations.Select;

/**
 * @author chengjindong
 * @description:
 * @date 2020/5/1414:47
 */
public interface KeywordMapper {
    @Select("SELECT  id as id ,keyword_name as keywordname,\n" +
            "keyword_value as keywordvalue,create_time as createtime,\n" +
            "update_time as updatetime ,version  as version\n" +
            "  FROM wechat_keywords where keyword_name=#{keyword};")
    WechatKeyword findByKeyword(String keyword);
}
