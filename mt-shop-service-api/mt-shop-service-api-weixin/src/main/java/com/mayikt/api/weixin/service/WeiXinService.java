package com.mayikt.api.weixin.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: chengjindong
 * @Date: 2020/4/16 22:34
 */
public interface WeiXinService {


    /**
     * 微信接口
     * @return
     */
    @GetMapping("/appInfo")
    String appInfo(@RequestParam("userId")Long userId);
}
