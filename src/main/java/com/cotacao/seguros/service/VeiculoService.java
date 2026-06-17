package com.cotacao.seguros.service;

import com.cotacao.seguros.dto.VeiculoRequestDTO;
import com.cotacao.seguros.entity.Veiculo;
import com.cotacao.seguros.exception.ResourceNotFoundException;
import com.cotacao.seguros.mapper.VeiculoMapper;
import com.cotacao.seguros.repository.VeiculoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VeiculoService {
    private final VeiculoRepository repo;
    private final VeiculoMapper mapper;

    @Autowired
    public VeiculoService(VeiculoRepository repo, VeiculoMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }


    public Veiculo cadastrar(VeiculoRequestDTO dto) {
        Veiculo entity = mapper.toEntity(dto);
        return repo.save(entity);
    }

    public List<Veiculo> listarTodos() {
        return repo.findAll();
    }

    public Veiculo buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado"));
    }
}
