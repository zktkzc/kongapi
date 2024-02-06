package com.tkzc00.kongapibackend.exception;

import com.tkzc00.kongapibackend.common.ErrorCode;

/**
 * 自定义异常类
 *
 * @author tkzc00
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -3932014170767714444L;

    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

    public int getCode() {
        return code;
    }
}
