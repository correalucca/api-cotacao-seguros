package com.cotacao.seguros.service;

import com.cotacao.seguros.dto.CotacaoRequestDTO;
import com.cotacao.seguros.dto.CotacaoResponseDTO;
import com.cotacao.seguros.entity.Cliente;
import com.cotacao.seguros.entity.Cotacao;
import com.cotacao.seguros.entity.Veiculo;
import com.cotacao.seguros.exception.ResourceNotFoundException;
import com.cotacao.seguros.mapper.CotacaoMapper;
import com.cotacao.seguros.repository.ClienteRepository;
import com.cotacao.seguros.repository.CotacaoRepository;
import com.cotacao.seguros.repository.VeiculoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CotacaoService {
    private final CotacaoRepository repo;
    private final CotacaoMapper mapper;
    private final ClienteRepository clienteRepo;
    private final VeiculoRepository veiculoRepo;

    @Autowired
    public CotacaoService(CotacaoRepository repo, CotacaoMapper mapper,
                          ClienteRepository clienteRepo, VeiculoRepository veiculoRepo) {
        this.repo = repo;
        this.mapper = mapper;
        this.clienteRepo = clienteRepo;
        this.veiculoRepo = veiculoRepo;
    }


    public CotacaoResponseDTO cadastrar(CotacaoRequestDTO dto) {
        Cotacao entity = mapper.toEntity(dto);
        Cliente cliente = clienteRepo.findById(dto.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        Veiculo veiculo = veiculoRepo.findById(dto.getVeiculoId())
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado"));
        entity.setCliente(cliente);
        entity.setVeiculo(veiculo);
        entity.setDataCriacao(LocalDateTime.now());
        return mapper.toDto(repo.save(entity));
    }

    public List<CotacaoResponseDTO> listarTodos() {
        return repo.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public CotacaoResponseDTO atualizar(Long id, CotacaoRequestDTO dto) {
        Cotacao existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cotação não encontrada"));
        mapper.updateEntityFromDto(dto, existing);
        if (dto.getClienteId() != null) {
            Cliente cliente = clienteRepo.findById(dto.getClienteId())
                    .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
            existing.setCliente(cliente);
        }
        if (dto.getVeiculoId() != null) {
            Veiculo veiculo = veiculoRepo.findById(dto.getVeiculoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado"));
            existing.setVeiculo(veiculo);
        }
        return mapper.toDto(repo.save(existing));
    }
}
