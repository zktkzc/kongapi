package com.tkzc00.kongapibackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tkzc00.kongapibackend.annotation.AuthCheck;
import com.tkzc00.kongapibackend.common.BaseResponse;
import com.tkzc00.kongapibackend.common.ErrorCode;
import com.tkzc00.kongapibackend.common.ResultUtils;
import com.tkzc00.kongapibackend.constant.UserConstant;
import com.tkzc00.kongapibackend.exception.BusinessException;
import com.tkzc00.kongapibackend.mapper.UserInterfaceInfoMapper;
import com.tkzc00.kongapibackend.model.vo.InterfaceInfoVO;
import com.tkzc00.kongapibackend.service.InterfaceInfoService;
import com.tkzc00.kongapicommon.model.entity.InterfaceInfo;
import com.tkzc00.kongapicommon.model.entity.UserInterfaceInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/analysis")
@CrossOrigin(origins = {"http://localhost:8000"}, allowCredentials = "true")
public class AnalysisController {
    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;
    @Resource
    private InterfaceInfoService interfaceInfoService;

    @GetMapping("/top/interface/invoke")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<List<InterfaceInfoVO>> listTopInvokeInterfaceInfo() {
        List<UserInterfaceInfo> userInterfaceInfos = userInterfaceInfoMapper.listTopInvokeInterfaceInfo(3);
        Map<Long, List<UserInterfaceInfo>> collect = userInterfaceInfos.stream().collect(Collectors.groupingBy(UserInterfaceInfo::getInterfaceInfoId));
        List<InterfaceInfo> interfaceInfoList = interfaceInfoService.list(new QueryWrapper<InterfaceInfo>()
                .in("id", collect.keySet()));
        if (interfaceInfoList.isEmpty()) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        List<InterfaceInfoVO> interfaceInfoVOList = interfaceInfoList.stream().map(interfaceInfo -> {
            InterfaceInfoVO interfaceInfoVO = new InterfaceInfoVO();
            BeanUtils.copyProperties(interfaceInfo, interfaceInfoVO);
            interfaceInfoVO.setTotalNum(collect.get(interfaceInfo.getId()).get(0).getTotalNum());
            return interfaceInfoVO;
        }).collect(Collectors.toList());
        return ResultUtils.success(interfaceInfoVOList);
    }
}
