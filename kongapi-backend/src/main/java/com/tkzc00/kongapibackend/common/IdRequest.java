package com.tkzc00.kongapibackend.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 删除请求
 *
 * @author tkzc00
 */
@Data
public class IdRequest implements Serializable {

    private static final long serialVersionUID = 7154311456084325235L;

    /**
     * id
     */
    private Long id;
}