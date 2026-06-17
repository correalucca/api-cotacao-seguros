package com.cotacao.seguros.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CotacaoResponseDTO {
    private Long id;
    private Long clienteId;
    private Long veiculoId;
    private BigDecimal valor;
    private LocalDateTime dataCriacao;
}
