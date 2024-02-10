package com.tkzc00.kongapibackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tkzc00.kongapibackend.common.ErrorCode;
import com.tkzc00.kongapibackend.exception.BusinessException;
import com.tkzc00.kongapibackend.mapper.UserMapper;
import com.tkzc00.kongapicommon.model.entity.User;
import com.tkzc00.kongapicommon.service.InnerUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@DubboService
public class InnerUserServiceImpl implements InnerUserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getInvokeUser(String accessKey) {
        if (accessKey == null || StringUtils.isBlank(accessKey)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        return userMapper.selectOne(new QueryWrapper<User>()
                .eq("accessKey", accessKey));
    }
}
