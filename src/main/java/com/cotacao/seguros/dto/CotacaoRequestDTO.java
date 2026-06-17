package com.cotacao.seguros.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CotacaoRequestDTO {
    private Long clienteId;
    private Long veiculoId;
    private BigDecimal valor;
}
