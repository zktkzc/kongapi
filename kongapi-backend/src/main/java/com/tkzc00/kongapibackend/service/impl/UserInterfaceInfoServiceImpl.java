package com.tkzc00.kongapibackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tkzc00.kongapibackend.common.ErrorCode;
import com.tkzc00.kongapibackend.exception.BusinessException;
import com.tkzc00.kongapibackend.mapper.UserInterfaceInfoMapper;
import com.tkzc00.kongapibackend.model.entity.UserInterfaceInfo;
import com.tkzc00.kongapibackend.service.UserInterfaceInfoService;
import org.springframework.stereotype.Service;

/**
 * @author tkzc00
 * @description 针对表【user_interface_info(用户接口关系表)】的数据库操作Service实现
 * @createDate 2024-02-08 15:04:04
 */
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
        implements UserInterfaceInfoService {

    @Override
    public void validInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为null");
        Long id = userInterfaceInfo.getId();
        Long userId = userInterfaceInfo.getUserId();
        Long interfaceInfoId = userInterfaceInfo.getInterfaceInfoId();
        Integer totalNum = userInterfaceInfo.getTotalNum();
        Integer leftNum = userInterfaceInfo.getLeftNum();
        if (add) {
            if (userId == null || userId <= 0) throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户id不合法");
            if (interfaceInfoId == null || interfaceInfoId <= 0)
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口id不合法");
            if (totalNum == null || totalNum < 0) throw new BusinessException(ErrorCode.PARAMS_ERROR, "总次数不合法");
            if (leftNum == null || leftNum < 0 || leftNum > totalNum)
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "剩余次数不合法");
        }
        if (totalNum < 0) throw new BusinessException(ErrorCode.PARAMS_ERROR, "总次数不能小于0");
        if (leftNum < 0) throw new BusinessException(ErrorCode.PARAMS_ERROR, "剩余次数不能小于0");
        if (leftNum > totalNum) throw new BusinessException(ErrorCode.PARAMS_ERROR, "剩余次数不能大于总次数");
    }

    @Override
    public boolean invokeCount(Long interfaceInfoId, Long userId) {
        if (interfaceInfoId == null || interfaceInfoId <= 0)
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口id不合法");
        if (userId == null || userId <= 0)
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户id不合法");
        return update(new UpdateWrapper<UserInterfaceInfo>().eq("interfaceInfoId", interfaceInfoId)
                .eq("userId", userId).ge("leftNum", 1)
                .setSql("leftNum = leftNum - 1, totalNum = totalNum + 1"));
    }
}




