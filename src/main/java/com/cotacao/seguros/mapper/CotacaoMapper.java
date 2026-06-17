package com.cotacao.seguros.mapper;

import com.cotacao.seguros.dto.CotacaoRequestDTO;
import com.cotacao.seguros.dto.CotacaoResponseDTO;
import com.cotacao.seguros.entity.Cotacao;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CotacaoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente", ignore = true) // set in service if needed
    @Mapping(target = "veiculo", ignore = true) // set in service if needed
    Cotacao toEntity(CotacaoRequestDTO dto);

    @Mapping(target = "clienteId", source = "cliente.id")
    @Mapping(target = "veiculoId", source = "veiculo.id")
    CotacaoResponseDTO toDto(Cotacao entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "veiculo", ignore = true)
    void updateEntityFromDto(CotacaoRequestDTO dto, @MappingTarget Cotacao entity);
}
