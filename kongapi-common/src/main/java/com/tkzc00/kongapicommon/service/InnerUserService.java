package com.tkzc00.kongapicommon.service;


import com.tkzc00.kongapicommon.model.entity.User;

/**
 * 用户服务
 *
 * @author yupi
 */
public interface InnerUserService {
    User getInvokeUser(String accessKey);
}
