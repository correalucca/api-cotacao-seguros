package com.cotacao.seguros.controller;

import com.cotacao.seguros.dto.VeiculoRequestDTO;
import com.cotacao.seguros.entity.Veiculo;
import com.cotacao.seguros.service.VeiculoService;
import com.cotacao.seguros.ApiResponse.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
    private final VeiculoService service;

    @Autowired
    public VeiculoController(VeiculoService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse<Veiculo> create(@Validated @RequestBody VeiculoRequestDTO dto) {
        return ApiResponse.success(service.cadastrar(dto));
    }

    @GetMapping
    public ApiResponse<List<Veiculo>> listAll() {
        return ApiResponse.success(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ApiResponse<Veiculo> get(@PathVariable Long id) {
        return ApiResponse.success(service.buscarPorId(id));
    }
}
