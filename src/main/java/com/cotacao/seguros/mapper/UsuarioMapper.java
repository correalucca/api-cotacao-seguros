package com.cotacao.seguros.mapper;

import com.cotacao.seguros.dto.UsuarioDTO;
import com.cotacao.seguros.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioDTO toDto(Usuario entity);
    Usuario toEntity(UsuarioDTO dto);
    void updateEntityFromDto(UsuarioDTO dto, @MappingTarget Usuario entity);
}