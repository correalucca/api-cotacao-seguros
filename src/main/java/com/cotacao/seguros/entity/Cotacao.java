package com.cotacao.seguros.entity;

import javax.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cotacao", indexes = {
    @Index(name = "idx_cliente_id", columnList = "cliente_id"),
    @Index(name = "idx_veiculo_id", columnList = "veiculo_id")
})
@Entity
public class Cotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;
    private BigDecimal valor;
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
}
