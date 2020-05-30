package com.mayikt.weixin.api.service;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.base.BaseResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "根据userId生成微信二维码连接")
public interface WeiXinQrCodeService {


    /**
     * 根据userId生成微信二维码连接
     *
     * @param userId
     * @return
     */
    @GetMapping("/getQrUrl")
    BaseResponse<JSONObject> getQrUrl(@RequestParam("userId") Long userId);
}
