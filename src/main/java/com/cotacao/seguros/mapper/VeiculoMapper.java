package com.cotacao.seguros.mapper;

import com.cotacao.seguros.dto.VeiculoRequestDTO;
import com.cotacao.seguros.entity.Veiculo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VeiculoMapper {
    Veiculo toEntity(VeiculoRequestDTO dto);
    void updateEntityFromDto(VeiculoRequestDTO dto, @MappingTarget Veiculo entity);
}
