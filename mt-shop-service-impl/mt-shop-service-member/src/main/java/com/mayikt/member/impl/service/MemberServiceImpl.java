package com.mayikt.member.impl.service;

import com.mayikt.member.api.service.MemberService;
import com.mayikt.member.impl.feign.WeiXinServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chengjindong
 * @Date: 2020/4/17 22:08
 */
@RestController
@RefreshScope // 此注解刷新配置中心更改的内容
public class MemberServiceImpl implements MemberService {

    @Autowired
    private WeiXinServiceFeign weiXinServiceFeign;

    @Value("${mayikit.name}")
    private String name;

    @Override
    public String memberToWeiXin(Long userId) {
        return weiXinServiceFeign.appInfo(userId);
    }

    @Override
    public String getConfig() {
        return name;
    }
}
