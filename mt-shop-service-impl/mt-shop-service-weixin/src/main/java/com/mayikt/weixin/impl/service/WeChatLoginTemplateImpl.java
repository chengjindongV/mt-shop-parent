package com.mayikt.weixin.impl.service;

import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import com.mayikt.utils.SimpleDateFormatUtils;
import com.mayikt.weixin.api.request.LoginTemplateDto;
import com.mayikt.weixin.api.service.WeChatLoginTemplate;
import com.mayikt.weixin.mp.config.WxMpConfiguration;
import com.mayikt.weixin.mp.config.WxMpProperties;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: WeChatLoginTemplateImpl
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/1822:17
 */
@RestController
public class WeChatLoginTemplateImpl extends BaseApiService implements WeChatLoginTemplate {
    @Autowired
    private WxMpProperties wxMpProperties;
    @Value("${mayikt.wx.loginTemplateId}")
    private String loginTemplateId;

    @Override
    public BaseResponse<Object> sendLoginTemplate(LoginTemplateDto loginTemplateDto) {

        String phone = loginTemplateDto.getPhone();
        if (StringUtils.isEmpty(phone)) {
            return setResultError("phone参数不能为空!");
        }
        String loginIp = loginTemplateDto.getLoginIp();
        if (StringUtils.isEmpty(phone)) {
            return setResultError("loginIp参数不能为空!");
        }
        Date loginTime =
                loginTemplateDto.getLoginTime();
        if (loginTime == null) {
            return setResultError("loginTime参数不能为空!");
        }
        String openId = loginTemplateDto.getOpenId();
        if (StringUtils.isEmpty(openId)) {
            return setResultError("loginIp参数不能为空!");
        }
        String equipment = loginTemplateDto.getEquipment();
        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        wxMpTemplateMessage.setTemplateId(loginTemplateId);
        wxMpTemplateMessage.setToUser(openId);
        List<WxMpTemplateData> data = new ArrayList<>();
        data.add(new WxMpTemplateData("first", phone));
        data.add(new WxMpTemplateData("keyword1",
                SimpleDateFormatUtils.getFormatStrByPatternAndDate(loginTime)));
        data.add(new WxMpTemplateData("keyword2", loginIp));
        data.add(new WxMpTemplateData("keyword3", equipment));
        wxMpTemplateMessage.setUrl("http://www.mayikt.com");
        wxMpTemplateMessage.setData(data);
        try {
            String appId = wxMpProperties.getConfigs().get(0).getAppId();
            WxMpTemplateMsgService templateMsgService = WxMpConfiguration.getMpServices().get(appId).getTemplateMsgService();
            templateMsgService.sendTemplateMsg(wxMpTemplateMessage);
            return setResultSuccess();
        } catch (Exception e) {
            return setResultError("发送失败");
        }
    }
    /**
     * 调用第三方接口发送消息  同步和异步 消息服务平台
     *
     * 走Http协议 会员调用 消息服务平台 调用微信接口/短信/
     * 异步形式 MQ
     *
     */
}
