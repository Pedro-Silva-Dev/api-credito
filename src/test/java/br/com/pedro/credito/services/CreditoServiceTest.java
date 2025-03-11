package br.com.pedro.credito.services;

import br.com.pedro.credito.models.Credito;
import br.com.pedro.credito.repositories.CreditoRepository;
import br.com.pedro.credito.util.CreditoUtilTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Testar o service de Credito.")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreditoServiceTest {

    @Autowired
    private CreditoService creditoService;

    @Mock
    private CreditoRepository creditoRepositoryMock;

    private Credito credito;

    @BeforeEach
    void setup() {
        this.credito = CreditoUtilTest.obterCredito(1L);
        BDDMockito.when(creditoRepositoryMock.findAllByNumeroNfse(this.credito.getNumeroNfse())).thenReturn(List.of(this.credito));
        BDDMockito.when(creditoRepositoryMock.findOneByNumeroCredito(this.credito.getNumeroCredito())).thenReturn(Optional.of(this.credito));
    }

    @Test
    @DisplayName("Obter uma lista de crédito por número de nfse.")
    void obterCreditosPorNumeroNfseTest() {
        String numeroNfse = this.credito.getNumeroNfse();
        List<Credito> results = creditoService.obterCreditosPorNumeroNfse(numeroNfse);
        assertThat(results).isNotEmpty();
        assertThat(results.get(0)).isNotNull();
        assertThat(results.get(0).getNumeroNfse()).isNotNull();
        assertThat(results.get(0).getNumeroNfse()).isEqualTo(numeroNfse);
    }

    @Test
    @DisplayName("Obter uma lista de crédito vazio ao passar o número de nfse inválido.")
    void obterCreditosPorNumeroNfseInvalidoTest() {
        List<Credito> results = creditoService.obterCreditosPorNumeroNfse(null);
        assertThat(results).isNull();
    }

    @Test
    @DisplayName("Obter uma lista de crédito vazio ao passar o número de nfse vazio.")
    void obterCreditosPorNumeroNfseVazioTest() {
        List<Credito> results = creditoService.obterCreditosPorNumeroNfse("");
        assertThat(results).isNull();
    }

    @Test
    @DisplayName("Obter uma lista de crédito vazio ao passar o número de nfse incorreto.")
    void obterCreditosPorNumeroNfseIncorretoTest() {
        String numeroNfseIncorreto = String.format("sA%s&Z", this.credito.getNumeroNfse());
        List<Credito> results = creditoService.obterCreditosPorNumeroNfse(numeroNfseIncorreto);
        assertThat(results).isNotNull();
        assertThat(results).isEmpty();
    }

    @Test
    @DisplayName("Obter um crédito por número de crédito.")
    void obterCreditoPorNumeroCreditoTest() {
        String numeroCredito = this.credito.getNumeroCredito();
        Credito result = creditoService.obterCreditoPorNumeroCredito(numeroCredito);
        assertThat(result).isNotNull();
        assertThat(result.getNumeroCredito()).isNotNull();
        assertThat(result.getNumeroCredito()).isEqualTo(numeroCredito);
    }

    @Test
    @DisplayName("Obter um crédito por número de crédito inválido.")
    void obterCreditoPorNumeroCreditoInvalidoTest() {
        Credito result = creditoService.obterCreditoPorNumeroCredito(null);
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Obter um crédito por número de crédito vazio.")
    void obterCreditoPorNumeroCreditoVazioTest() {
        Credito result = creditoService.obterCreditoPorNumeroCredito("");
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Obter um crédito por número de crédito incorreto.")
    void obterCreditoPorNumeroNfseIncorretoTest() {
        String numeroCreditoIncorreto = String.format("sA%s&Z", this.credito.getNumeroCredito());
        Credito result = creditoService.obterCreditoPorNumeroCredito(numeroCreditoIncorreto);
        assertThat(result).isNull();
    }

}