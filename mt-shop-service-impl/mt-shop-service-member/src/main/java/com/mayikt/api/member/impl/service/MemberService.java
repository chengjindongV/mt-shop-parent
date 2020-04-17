package com.mayikt.api.member.impl.service;

import com.mayikt.api.member.impl.feign.WeiXinServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chengjindong
 * @Date: 2020/4/17 22:08
 */
@RestController
public class MemberService {

    @Autowired
    private WeiXinServiceFeign weiXinServiceFeign;

    @GetMapping("/memberToWeiXin")
    public String memberToWeiXin(Long userId){
        return "会员服务调用：" + weiXinServiceFeign.appInfo(userId);
    }
}
