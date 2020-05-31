package com.mayikt.member.impl.feign;

import com.mayikt.weixin.api.service.WeChatLoginTemplate;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: WeChatLoginTemplateFeign
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/1822:32
 */
@FeignClient("mayikt-weixin")
public interface WeChatLoginTemplateFeign extends WeChatLoginTemplate {

}
