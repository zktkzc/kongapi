package com.tkzc00.kongapibackend.service.impl;

import java.util.Date;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tkzc00.kongapibackend.common.ErrorCode;
import com.tkzc00.kongapibackend.exception.BusinessException;
import com.tkzc00.kongapibackend.model.entity.InterfaceInfo;
import com.tkzc00.kongapibackend.service.InterfaceInfoService;
import com.tkzc00.kongapibackend.mapper.InterfaceInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author tkzc00
 * @description 针对表【interface_info(接口信息表)】的数据库操作Service实现
 * @createDate 2024-02-06 13:05:59
 */
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo> implements InterfaceInfoService {

    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
        if (interfaceInfo == null) throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为null");
        String name = interfaceInfo.getName();
        String description = interfaceInfo.getDescription();
        String url = interfaceInfo.getUrl();
        String method = interfaceInfo.getMethod();
        Long userId = interfaceInfo.getUserId();
        if (add) {
            if (StringUtils.isAnyBlank(name)) throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口名称不能为空");
            if (StringUtils.isAnyBlank(url)) throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口地址不能为空");
            if (StringUtils.isAnyBlank(description)) throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口描述不能为空");
            if (StringUtils.isAnyBlank(method)) throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求类型不能为空");
        }
        if (StringUtils.isNotBlank(name) && name.length() > 100)
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口名称过长");
    }
}




