package com.mayikt.member.impl.feign;

import com.mayikt.weixin.api.service.WeiXinService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: chengjindong
 * @Date: 2020/4/17 22:11
 *
 * feign 客户端复用代码，不需要写每个需要调用的接口 在这里再写一遍，可以在当前项目pom文件中引入需要调用接口的jar包，直接继承需要调用的接口
 */
@FeignClient("mayikt-weixin")
public interface WeiXinServiceFeign extends WeiXinService {

//    /**
//     * 微信接口
//     * @return
//     */
//    @GetMapping("/appInfo")
//    String appInfo(@RequestParam("userId") Long userId);
}
