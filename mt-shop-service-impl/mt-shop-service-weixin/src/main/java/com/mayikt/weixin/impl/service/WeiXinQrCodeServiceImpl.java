package com.mayikt.weixin.impl.service;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import com.mayikt.weixin.api.service.WeiXinQrCodeService;
import com.mayikt.weixin.constant.WeiXinConstant;
import com.mayikt.weixin.mp.config.WxMpConfiguration;
import com.mayikt.weixin.mp.config.WxMpProperties;
import me.chanjar.weixin.mp.api.WxMpQrcodeService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;

/**
 * @author chengjindong
 * @title: WeiXinQrCodeServiceImpl
 * @description: 根据用户id生成二维码接口
 * @date 2020/2/3023:00
 */
@RestController
public class WeiXinQrCodeServiceImpl extends BaseApiService implements WeiXinQrCodeService {
    @Autowired
    private WxMpProperties wxMpProperties;

    @Override
    public BaseResponse<JSONObject> getQrUrl(Long userId) {
        if (userId == null) {
            return setResultError("userId不能为空!");
        }
        //获取配置第一个appId
        String appId = wxMpProperties.getConfigs().get(0).getAppId();
        // 根据appid 获取对应的  WxMpQrcodeService
        WxMpQrcodeService qrcodeService = WxMpConfiguration.getMpServices().get(appId).getQrcodeService();
        try {
            WxMpQrCodeTicket wxMpQrCodeTicket = qrcodeService.qrCodeCreateTmpTicket(userId + "",
                    WeiXinConstant.QR_CODE_EXPIRE_SECONDS);
            if (wxMpQrCodeTicket == null) {
                return setResultError("生成二维码链接失败!");
            }
            String ticket = wxMpQrCodeTicket.getTicket();
            return setResultSuccess(URLEncoder.encode(ticket,"UTF-8"));
        } catch (Exception e) {
            return setResultError("生成二维码链接失败!");
        }
    }
}
