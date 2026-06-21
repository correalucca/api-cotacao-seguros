package com.cotacao.seguros.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "veiculo", indexes = {
    @Index(name = "idx_placa", columnList = "placa")
})
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String placa;
    private String modelo;
    private String marca;
    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("veiculo")
    private List<Cotacao> cotacoes;
}
