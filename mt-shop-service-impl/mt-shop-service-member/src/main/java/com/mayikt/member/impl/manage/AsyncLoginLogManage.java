package com.mayikt.member.impl.manage;

import com.mayikt.member.impl.entitydo.UserLoginLogDo;
import com.mayikt.member.impl.mapper.UserLoginLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author chengjindong
 * @title: AsyncLoginLogManage
 */
@Component
@Slf4j
public class AsyncLoginLogManage {
    @Autowired
    private UserLoginLogMapper userLoginLogMapper;
    @Async
    public void loginLog(Long userId, String loginIp, Date loginTime, String loginToken, String channel,
                         String equipment) {
        UserLoginLogDo userLoginLogDo = new UserLoginLogDo(userId, loginIp, loginTime, loginToken, channel, equipment);
        log.info(Thread.currentThread().getName() + ",userLoginLogDo:" + userLoginLogDo.toString() + ",流程2");
        userLoginLogMapper.insertUserLoginLog(userLoginLogDo);
        log.info(Thread.currentThread().getName() + " 处理流程2");
    }
}
