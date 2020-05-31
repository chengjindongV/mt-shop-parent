package com.mayikt.member.impl.service;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import com.mayikt.member.api.dto.req.UserLoginDto;
import com.mayikt.member.api.service.MemberLoginService;
import com.mayikt.member.impl.entitydo.UserDo;
import com.mayikt.member.impl.entitydo.UserLoginLogDo;
import com.mayikt.member.impl.manage.AsyncLoginLogManage;
import com.mayikt.member.impl.mapper.UserLoginLogMapper;
import com.mayikt.member.impl.mapper.UserMapper;
import com.mayikt.member.impl.utils.ChannelUtils;
import com.mayikt.utils.MD5Util;
import com.mayikt.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: MemberLoginServiceImpl
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/1221:59
 */
@RestController
@Slf4j
public class MemberLoginServiceImpl extends BaseApiService implements MemberLoginService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenUtils tokenUtils;
    @Value("${mayikt.login.token.prefix}")
    private String loginTokenPrefix;
    @Autowired
    private AsyncLoginLogManage asyncLoginLogManage;
    @Autowired
    private UserLoginLogMapper userLoginLogMapper;
    @Autowired
    private ChannelUtils channelUtils;

    @Override
    public BaseResponse<JSONObject> login(UserLoginDto userLoginDto, String sourceIp
            , String channel, String deviceInfor) {
        // 参数验证
        String mobile = userLoginDto.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("mobile参数不能为空");
        }
        String passWord = userLoginDto.getPassWord();
        if (StringUtils.isEmpty(userLoginDto.getPassWord())) {
            return setResultError("passWord参数不能为空");
        }
        // 查询我们的数据库
        String newPassWord = MD5Util.MD5(passWord);
        UserDo loginUserDo = userMapper.login(mobile, newPassWord);
        if (loginUserDo == null) {
            return setResultError("手机号码或者密码不正确!");
        }

        // 设备信息
        if (StringUtils.isEmpty(deviceInfor)) {
            return setResultError("设备信息不能为空!");
        }
        if (!channelUtils.existChannel(channel)) {
            return setResultError("渠道来源错误!");
        }
        //获取userId
        Long userId = loginUserDo.getUserId();
        String userToken = tokenUtils.createToken(loginTokenPrefix, userId + "");
        /**
         * data{
         *     "userToken":""
         * }
         */
        JSONObject resultJSON = new JSONObject();
        resultJSON.put("userToken", userToken);
        String wxOpenId = loginUserDo.getWxOpenId();
        asyncLoginLogManage.loginLog(wxOpenId, mobile, userId, sourceIp, new Date(), userToken
                , channel, deviceInfor);
        return setResultSuccess(resultJSON);
    }


    /**
     * 异步写入日志 线程池如何配置
     */


    public void loginLog(Long userId, String loginIp, Date loginTime, String loginToken, String channel,
                         String equipment) {
        UserLoginLogDo userLoginLogDo = new UserLoginLogDo(userId, loginIp, loginTime, loginToken, channel, equipment);
        log.info(Thread.currentThread().getName() + ",userLoginLogDo:" + userLoginLogDo.toString() + ",流程2");
        userLoginLogMapper.insertUserLoginLog(userLoginLogDo);
        log.info(Thread.currentThread().getName() + " 处理流程2");
    }

    /**
     *     @Async 导致我们springmvc请求404
     *    @Async不生效
     */


    /**
     * 注意的事项
     * 建议最好单独使用一个累嗲我用  @Async
     *
     * token一定是有超时时间
     *
     * 记住密码  设置有效期
     *
     * 默认时间 最长90天 7天
     */

}
