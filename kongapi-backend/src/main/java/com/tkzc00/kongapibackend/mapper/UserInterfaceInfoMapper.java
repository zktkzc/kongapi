package com.tkzc00.kongapibackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tkzc00.kongapicommon.model.entity.UserInterfaceInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author tkzc00
* @description 针对表【user_interface_info(用户接口关系表)】的数据库操作Mapper
* @createDate 2024-02-08 15:04:04
*/
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {
    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(@Param("limit") int limit);
}




