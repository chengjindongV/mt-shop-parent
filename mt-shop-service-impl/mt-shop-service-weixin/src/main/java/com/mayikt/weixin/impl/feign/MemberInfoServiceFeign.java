package com.mayikt.weixin.impl.feign;

import com.mayikt.member.api.service.MemberInfoService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: MemberInfoServiceFeign
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/1821:56
 */
@FeignClient("mayikt-member")
public interface MemberInfoServiceFeign extends MemberInfoService {
}
