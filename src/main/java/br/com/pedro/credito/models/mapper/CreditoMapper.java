package br.com.pedro.credito.models.mapper;

import br.com.pedro.credito.models.Credito;
import br.com.pedro.credito.models.dto.CreditoDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "creditoMapper")
public abstract class CreditoMapper {

    public static final CreditoMapper INSTANCE = Mappers.getMapper(CreditoMapper.class);

    public abstract CreditoDto toCreditoDto(Credito credito);

}
