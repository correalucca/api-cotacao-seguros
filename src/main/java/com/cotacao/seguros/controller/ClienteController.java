package com.cotacao.seguros.controller;

import com.cotacao.seguros.dto.ClienteRequestDTO;
import com.cotacao.seguros.entity.Cliente;
import com.cotacao.seguros.service.ClienteService;
import com.cotacao.seguros.ApiResponse.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService service;

    @Autowired
    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse<Cliente> create(@Validated @RequestBody ClienteRequestDTO dto) {
        return ApiResponse.success(service.cadastrar(dto));
    }

    @GetMapping
    public ApiResponse<List<Cliente>> listAll() {
        return ApiResponse.success(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ApiResponse<Cliente> get(@PathVariable Long id) {
        return ApiResponse.success(service.buscarPorId(id));
    }
}
