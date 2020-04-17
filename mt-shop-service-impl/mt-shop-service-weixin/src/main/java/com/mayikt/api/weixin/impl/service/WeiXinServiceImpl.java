package com.mayikt.api.weixin.impl.service;

import com.mayikt.api.weixin.service.WeiXinService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chengjindong
 * @Date: 2020/4/16 22:55
 */
@RestController
public class WeiXinServiceImpl implements WeiXinService {

    @Override
    public String appInfo(Long userId) {
        return "微信接口" + userId;
    }
}
