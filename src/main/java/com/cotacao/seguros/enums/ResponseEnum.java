package com.cotacao.seguros.enums;

public enum ResponseEnum {
    SUCCESS(0, "OK"),
    ERROR(-1, "Erro interno do servidor"),
    PARAM_ERROR(1, "Parâmetro inválido"),
    DUPLICATE_EMAIL(2, "E-mail já cadastrado"),
    DUPLICATE_CPF(3, "CPF já cadastrado"),
    NOT_FOUND(4, "Recurso não encontrado"),
    UNAUTHORIZED(5, "Não autorizado"),
    VALIDATION_ERROR(6, "Erro de validação");

    private final Integer code;
    private final String message;

    ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
