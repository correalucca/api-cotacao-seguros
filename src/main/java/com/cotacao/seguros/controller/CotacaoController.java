package com.cotacao.seguros.controller;

import com.cotacao.seguros.dto.CotacaoRequestDTO;
import com.cotacao.seguros.dto.CotacaoResponseDTO;
import com.cotacao.seguros.service.CotacaoService;
import com.cotacao.seguros.ApiResponse.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cotacoes")
public class CotacaoController {
    private final CotacaoService service;

    @Autowired
    public CotacaoController(CotacaoService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse<CotacaoResponseDTO> create(@Validated @RequestBody CotacaoRequestDTO dto) {
        return ApiResponse.success(service.cadastrar(dto));
    }

    @GetMapping
    public ApiResponse<List<CotacaoResponseDTO>> listAll() {
        return ApiResponse.success(service.listarTodos());
    }

    @PutMapping("/{id}")
    public ApiResponse<CotacaoResponseDTO> update(@PathVariable Long id,
                                                  @Validated @RequestBody CotacaoRequestDTO dto) {
        return ApiResponse.success(service.atualizar(id, dto));
    }
}
