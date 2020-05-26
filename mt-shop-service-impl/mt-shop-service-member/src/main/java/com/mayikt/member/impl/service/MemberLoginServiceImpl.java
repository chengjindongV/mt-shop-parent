package com.mayikt.member.impl.service;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import com.mayikt.member.api.dto.req.UserLoginDto;
import com.mayikt.member.api.service.MemberLoginService;
import com.mayikt.member.impl.entitydo.UserDo;
import com.mayikt.member.impl.manage.AsyncLoginLogManage;
import com.mayikt.member.impl.mapper.UserMapper;
import com.mayikt.utils.MD5Util;
import com.mayikt.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author chengjindong
 * @title: MemberLoginServiceImpl
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


    @Override
    public BaseResponse<JSONObject> login(UserLoginDto userLoginDto) {
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

        // 写入日志
        log.info(Thread.currentThread().getName() + " 处理流程1");
        asyncLoginLogManage.loginLog(userId, "192.168.212.110", new Date(), userToken
                , "PC", "windows 谷歌浏览器");
        log.info(Thread.currentThread().getName() + " 处理流程3");
        return setResultSuccess(resultJSON);
    }
    /**
     * 异步写入日志 线程池如何配置
     */

}
