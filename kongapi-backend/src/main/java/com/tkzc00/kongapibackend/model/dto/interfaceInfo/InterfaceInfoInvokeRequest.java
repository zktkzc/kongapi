package com.tkzc00.kongapibackend.model.dto.interfaceInfo;

import lombok.Data;

import java.io.Serializable;

/**
 * 接口调用请求
 *
 * @author tkzc00
 */
@Data
public class InterfaceInfoInvokeRequest implements Serializable {

    private static final long serialVersionUID = -1847275735069822524L;

    private Long id;

    /**
     * 请求参数
     */
    private String userRequestParams;
}
