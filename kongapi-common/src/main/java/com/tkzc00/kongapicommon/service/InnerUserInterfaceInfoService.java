package com.tkzc00.kongapicommon.service;


/**
 * @author tkzc00
 * @description 针对表【user_interface_info(用户接口关系表)】的数据库操作Service
 * @createDate 2024-02-08 15:04:04
 */
public interface InnerUserInterfaceInfoService {
    boolean invokeCount(Long interfaceInfoId, Long userId);
}
