package com.mayikt.weixin.impl.manager;

import com.mayikt.base.BaseResponse;
import com.mayikt.constant.HttpConstant;
import com.mayikt.member.api.dto.resp.UserRespDto;
import com.mayikt.weixin.impl.feign.MemberInfoServiceFeign;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: WxMpServiceManage
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/1821:45
 */
@Component
public class WxMpServiceManage {
    @Autowired
    private MemberInfoServiceFeign memberInfoServiceFeign;

    public WxMpXmlOutMessage handler(Long userId, String openId) {
        // 先根据openid 查询是否已经关联  ---会员服务
        BaseResponse<UserRespDto> userRespDtoBaseResponse = memberInfoServiceFeign.selectByOpenId(openId);
        if (userRespDtoBaseResponse.getCode().equals(HttpConstant.RPC_RESULT_SUCCESS)) {
            return  null;
        }
        // 如果没有关注过的情况下  update ---会员服务
        memberInfoServiceFeign.updateUseOpenId(userId,openId);
        return null;
    }
}
