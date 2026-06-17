package com.cotacao.seguros.exception;

import com.cotacao.seguros.enums.ResponseEnum;

public class BusinessException extends RuntimeException {
    private final Integer code;

    public BusinessException(ResponseEnum responseEnum) {
        super(responseEnum.getMessage());
        this.code = responseEnum.getCode();
    }

    public BusinessException(ResponseEnum responseEnum, String message) {
        super(message);
        this.code = responseEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
