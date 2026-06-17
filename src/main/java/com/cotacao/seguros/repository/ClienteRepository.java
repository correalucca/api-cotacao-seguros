package com.cotacao.seguros.repository;

import com.cotacao.seguros.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> { }
