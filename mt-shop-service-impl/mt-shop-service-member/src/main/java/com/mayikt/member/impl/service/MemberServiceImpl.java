package com.mayikt.member.impl.service;

import com.mayikt.member.api.service.MemberService;
import com.mayikt.member.impl.feign.WeiXinServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chengjindong
 * @Date: 2020/4/17 22:08
 */
@RestController
public class MemberServiceImpl implements MemberService {

    @Autowired
    private WeiXinServiceFeign weiXinServiceFeign;

    @Override
    public String memberToWeiXin(Long userId) {
        return weiXinServiceFeign.appInfo(userId);
    }
}
