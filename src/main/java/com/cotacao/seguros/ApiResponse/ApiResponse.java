package com.cotacao.seguros.ApiResponse;

import com.cotacao.seguros.enums.ResponseEnum;

public class ApiResponse<T> {
    private Integer code;
    private String message;
    private T data;

    private ApiResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMessage(), data);
    }

    public static <T> ApiResponse<T> success() {
        return success(null);
    }

    public static <T> ApiResponse<T> error(ResponseEnum responseEnum) {
        return new ApiResponse<>(responseEnum.getCode(), responseEnum.getMessage(), null);
    }

    public static <T> ApiResponse<T> error(ResponseEnum responseEnum, String message) {
        return new ApiResponse<>(responseEnum.getCode(), message, null);
    }

    public static <T> ApiResponse<T> error(Integer code, String message) {
        return new ApiResponse<>(code, message, null);
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
