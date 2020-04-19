package com.mayikt.weixin.impl.service;

import com.mayikt.weixin.api.service.WeiXinService;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chengjindong
 * @Date: 2020/4/16 22:55
 */
@RestController
public class WeiXinServiceImpl extends BaseApiService implements WeiXinService {

    @Override
    public String appInfo(Long userId) {
        return "微信接口" + userId;
    }

    @Override
    public BaseResponse<String> addApp(String appId, String appPwd) {
        if (StringUtils.isEmpty(appId)){
            return setResultError("appId不能为空");
        }

        if (StringUtils.isEmpty(appPwd)){
            return setResultError("appPwd不能为空");
        }
        return setResultSuccess("success");
    }


}
