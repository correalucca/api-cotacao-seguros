package com.cotacao.seguros.repository;

import com.cotacao.seguros.entity.Cotacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CotacaoRepository extends JpaRepository<Cotacao, Long> { }
