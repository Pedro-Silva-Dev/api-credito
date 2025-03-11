package br.com.pedro.credito.models.mapper;

import br.com.pedro.credito.models.Credito;
import br.com.pedro.credito.models.dto.CreditoDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-10T23:42:53-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.14 (Ubuntu)"
)
public class CreditoMapperImpl extends CreditoMapper {

    @Override
    public CreditoDto toCreditoDto(Credito credito) {
        if ( credito == null ) {
            return null;
        }

        CreditoDto.CreditoDtoBuilder creditoDto = CreditoDto.builder();

        creditoDto.numeroCredito( credito.getNumeroCredito() );
        creditoDto.numeroNfse( credito.getNumeroNfse() );
        creditoDto.dataConstituicao( credito.getDataConstituicao() );
        creditoDto.valorIssqn( credito.getValorIssqn() );
        creditoDto.tipoCredito( credito.getTipoCredito() );
        creditoDto.simplesNacional( credito.isSimplesNacional() );
        creditoDto.aliquota( credito.getAliquota() );
        creditoDto.valorFaturado( credito.getValorFaturado() );
        creditoDto.valorDeducao( credito.getValorDeducao() );
        creditoDto.baseCalculo( credito.getBaseCalculo() );

        return creditoDto.build();
    }
}
