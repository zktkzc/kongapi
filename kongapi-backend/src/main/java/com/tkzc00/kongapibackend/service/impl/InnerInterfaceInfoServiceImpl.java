package com.tkzc00.kongapibackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tkzc00.kongapibackend.common.ErrorCode;
import com.tkzc00.kongapibackend.exception.BusinessException;
import com.tkzc00.kongapibackend.mapper.InterfaceInfoMapper;
import com.tkzc00.kongapicommon.model.entity.InterfaceInfo;
import com.tkzc00.kongapicommon.service.InnerInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@DubboService
public class InnerInterfaceInfoServiceImpl implements InnerInterfaceInfoService {
    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;

    @Override
    public InterfaceInfo getInterfaceInfo(String url, String method) {
        if (url == null || method == null || url.isEmpty() || method.isEmpty())
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        return interfaceInfoMapper.selectOne(new QueryWrapper<InterfaceInfo>()
                .eq("url", url)
                .eq("method", method));
    }
}
