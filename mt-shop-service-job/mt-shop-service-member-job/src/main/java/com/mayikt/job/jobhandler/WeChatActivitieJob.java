package com.mayikt.job.jobhandler;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.job.entitydo.UserDo;
import com.mayikt.job.mapper.UserMapper;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.util.ShardingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 定时任务类
 */
@Component
@Slf4j
public class WeChatActivitieJob {
    @Autowired
    private UserMapper userMapper;
    @Value("${mayikt.member.job.WeChatActivitiePageSize}")
    private Integer pageSize;

    /**
     * @param param
     * @return
     * @XxlJob 该任务的名称id
     */
    @XxlJob("weChatActivitieJobHandler")
    public ReturnT<String> weChatActivitieJobHandler(String param) {
        ShardingUtil.ShardingVO shardingVO = ShardingUtil.getShardingVo();
        int index = shardingVO.getIndex();
        int startIndex = ((index + 1) - 1) * pageSize;
        log.info(">>>定时任务开始出发<<<param:{},index:{}", param, index);
        List<UserDo> userDos = userMapper.selectByOpenIdNotIsNull(startIndex, pageSize);
        log.info("userDos:" + JSONObject.toJSONString(userDos));
        return ReturnT.SUCCESS;
    }

    /**
     * 假设现在我们的每个执行执行2条  pageSize
     * index =0  第index=0+1  satart =(1-1)*pageSize, pageSize
     *
     * 1.执行器发送一半如果失败的情况下 5000-1000
     * 执行宁愿配置多一台也能少配  人工补偿
     * 2.在方法上@XxlJob即可
     * 3. 每个执行器发送10万 100万
     *
     */

}
