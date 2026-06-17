package com.cotacao.seguros.service;

import com.cotacao.seguros.dto.ClienteRequestDTO;
import com.cotacao.seguros.entity.Cliente;
import com.cotacao.seguros.exception.ResourceNotFoundException;
import com.cotacao.seguros.mapper.ClienteMapper;
import com.cotacao.seguros.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClienteService {
    private final ClienteRepository repo;
    private final ClienteMapper mapper;

    @Autowired
    public ClienteService(ClienteRepository repo, ClienteMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public Cliente cadastrar(ClienteRequestDTO dto) {
        Cliente entity = mapper.toEntity(dto);
        return repo.save(entity);
    }

    public List<Cliente> listarTodos() {
        return repo.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
    }
}
