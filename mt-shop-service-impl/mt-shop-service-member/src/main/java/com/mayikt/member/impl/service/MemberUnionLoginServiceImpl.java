package com.mayikt.member.impl.service;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import com.mayikt.member.api.service.MemberUnionLoginService;
import com.mayikt.member.impl.entitydo.UnionLoginDo;
import com.mayikt.member.impl.mapper.UnionLoginMapper;
import com.mayikt.member.impl.strategy.UnionLoginStrategy;
import com.mayikt.utils.SpringContextUtils;
import com.mayikt.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: MemberUnionLoginServiceImpl
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/2121:55
 */
@RestController
public class MemberUnionLoginServiceImpl extends BaseApiService implements MemberUnionLoginService {
    @Autowired
    private UnionLoginMapper unionLoginMapper;
    @Autowired
    private TokenUtils tokenUtils;


    @Override
    public BaseResponse<String> unionLogin(String unionPublicId) {
        if (StringUtils.isEmpty(unionPublicId)) {
            return setResultError("unionPublicId不能为空");
        }
        // 根据渠道id查询 联合基本信息
        UnionLoginDo unionLoginDo = unionLoginMapper.selectByUnionLoginId(unionPublicId);
        if (unionLoginDo == null) {
            return setResultError("该渠道可能已经关闭或者不存在");
        }
        String state = tokenUtils.createToken("member.unionLogin", "");
        String requestAddres = unionLoginDo.getRequestAddress() + "&state=" + state;
        JSONObject dataObjects = new JSONObject();
        dataObjects.put("requestAddres", requestAddres);
        return setResultSuccess(dataObjects);

    }

    @Override
    public BaseResponse<JSONObject> unionLoginCallback(String unionPublicId) {
        if (StringUtils.isEmpty(unionPublicId)) {
            return setResultError("unionPublicId不能为空");
        }
        // 根据渠道id查询 联合基本信息
        UnionLoginDo unionLoginDo = unionLoginMapper.selectByUnionLoginId(unionPublicId);
        if (unionLoginDo == null) {
            return setResultError("该渠道可能已经关闭或者不存在");
        }
        String unionBeanId = unionLoginDo.getUnionBeanId();
        if (StringUtils.isEmpty(unionBeanId)) {
            return setResultError("系统参数错误");
        }
        //  从Spring容器中根据beanid 查找到我们的策略类
        UnionLoginStrategy unionLoginStrategy = SpringContextUtils.getBean(unionBeanId, UnionLoginStrategy.class);
        // 根据当前线程获取request对象
        HttpServletRequest request = ((ServletRequestAttributes)
                (RequestContextHolder.currentRequestAttributes())).getRequest();
        String openId = unionLoginStrategy.unionLoginCallback(request, unionLoginDo);
        if (StringUtils.isEmpty(openId)) {
            return setResultError("系统错误");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("openId", openId);
        jsonObject.put("unionPublicId", unionPublicId);
        String openToken = tokenUtils.createToken("mayikt.unionLogin.", jsonObject.toJSONString());
        JSONObject dataToken = new JSONObject();
        dataToken.put("openToken", openToken);
        return setResultSuccess(dataToken);
    }
}
