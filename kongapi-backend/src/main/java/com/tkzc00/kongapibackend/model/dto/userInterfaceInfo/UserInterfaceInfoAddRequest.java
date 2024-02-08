package com.tkzc00.kongapibackend.model.dto.userInterfaceInfo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInterfaceInfoAddRequest implements Serializable {

    private static final long serialVersionUID = -2948137959810369771L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 接口id
     */
    private Long interfaceInfoId;

    /**
     * 总调用次数
     */
    private Integer totalNum;

    /**
     * 剩余调用次数
     */
    private Integer leftNum;
}
