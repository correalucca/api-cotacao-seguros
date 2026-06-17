package com.cotacao.seguros.mapper;

import com.cotacao.seguros.dto.ClienteRequestDTO;
import com.cotacao.seguros.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente toEntity(ClienteRequestDTO dto);
    void updateEntityFromDto(ClienteRequestDTO dto, @MappingTarget Cliente entity);
}
