package com.tkzc00.kongapibackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tkzc00.kongapicommon.model.entity.InterfaceInfo;

/**
* @author tkzc00
* @description 针对表【interface_info(接口信息表)】的数据库操作Service
* @createDate 2024-02-06 13:05:59
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
