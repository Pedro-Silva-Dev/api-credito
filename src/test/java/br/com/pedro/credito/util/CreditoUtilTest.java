package br.com.pedro.credito.util;

import br.com.pedro.credito.models.Credito;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreditoUtilTest {

    public static Credito obterCredito(Long id) {
        return Credito.builder()
                .id(id)
                .numeroCredito("123456")
                .numeroNfse("7891011")
                .dataConstituicao(LocalDate.now())
                .valorIssqn(BigDecimal.valueOf(1500.75))
                .tipoCredito("ISSQN")
                .simplesNacional(true)
                .aliquota(BigDecimal.valueOf(5.0))
                .valorFaturado(BigDecimal.valueOf(30000.00))
                .valorDeducao(BigDecimal.valueOf(5000.00))
                .baseCalculo(BigDecimal.valueOf(25000.00))
                .build();
    }

    public static Credito obterCreditoParaCadastro() {
        return Credito.builder()
                .numeroCredito("123456")
                .numeroNfse("7891011")
                .dataConstituicao(LocalDate.now())
                .valorIssqn(BigDecimal.valueOf(1500.75))
                .tipoCredito("ISSQN")
                .simplesNacional(true)
                .aliquota(BigDecimal.valueOf(5.0))
                .valorFaturado(BigDecimal.valueOf(30000.00))
                .valorDeducao(BigDecimal.valueOf(5000.00))
                .baseCalculo(BigDecimal.valueOf(25000.00))
                .build();
    }


}
