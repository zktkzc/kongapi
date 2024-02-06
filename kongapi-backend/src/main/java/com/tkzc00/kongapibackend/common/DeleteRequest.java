package com.tkzc00.kongapibackend.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 删除请求
 *
 * @author tkzc00
 */
@Data
public class DeleteRequest implements Serializable {
    private static final long serialVersionUID = 3513766978746569812L;

    /**
     * id
     */
    private Long id;
}