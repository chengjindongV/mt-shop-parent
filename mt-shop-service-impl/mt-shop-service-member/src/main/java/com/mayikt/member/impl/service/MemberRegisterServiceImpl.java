package com.mayikt.member.impl.service;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import com.mayikt.member.api.dto.req.UserReqRegisterDto;
import com.mayikt.member.api.service.MemberRegisterService;
import com.mayikt.member.impl.entitydo.UserDo;
import com.mayikt.member.impl.mapper.UserMapper;
import com.mayikt.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chengjindong
 * @title: MemberRegisterServiceImpl
 */
@RestController
public class MemberRegisterServiceImpl extends BaseApiService implements MemberRegisterService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseResponse<JSONObject> register(UserReqRegisterDto userReqRegisterDto) {
        // 参数验证
        String mobile = userReqRegisterDto.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("mobile参数不能为空");
        }
        String passWord = userReqRegisterDto.getPassWord();
        if (StringUtils.isEmpty(userReqRegisterDto.getPassWord())) {
            return setResultError("passWord参数不能为空");
        }
        // 先检查该手机号码是否存在
        UserDo userDbDo = userMapper.existMobile(mobile);
        if (userDbDo != null) {
            return setResultError("该手机号码已经存在");
        }
        // dto转换成do
        // 插入数据库中
        UserDo userDo = dtoToDo(userReqRegisterDto, UserDo.class);
        String newPassWord = MD5Util.MD5(passWord);
        userDo.setPassWord(newPassWord);
        //盐 密码+手机号码、提前定义盐值常量
        int register = userMapper.register(userDo);
//        return register > 0 ? setResultSuccess("注册成功") :
//                setResultError("注册失败");
        return setResult(register, "注册成功", "注册失败");
    }
    /**
     * 密码加密 MD5 +加盐值  单加密 解密 破解
     */
}
