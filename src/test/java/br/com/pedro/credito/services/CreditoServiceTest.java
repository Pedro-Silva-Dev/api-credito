package br.com.pedro.credito.services;

import br.com.pedro.credito.models.Credito;
import br.com.pedro.credito.repositories.CreditoRepository;
import br.com.pedro.credito.util.CreditoUtilTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    }

    @Test
    @DisplayName("Obter uma lista de credito por numero de nfse.")
    void obterCreditoPorNumeroNfseTest() {
        String numeroNfse = this.credito.getNumeroNfse();
        List<Credito> results = creditoService.obterCreditoPorNumeroNfse(numeroNfse);
        assertThat(results).isNotEmpty();
        assertThat(results.get(0)).isNotNull();
        assertThat(results.get(0).getNumeroNfse()).isNotNull();
        assertThat(results.get(0).getNumeroNfse()).isEqualTo(numeroNfse);
    }

    @Test
    @DisplayName("Obter uma lista de credito vazio ao passar o numero de nfse inválido.")
    void obterCreditoPorNumeroNfseInvalidoTest() {
        List<Credito> results = creditoService.obterCreditoPorNumeroNfse(null);
        assertThat(results).isNull();
    }

    @Test
    @DisplayName("Obter uma lista de credito vazio ao passar o numero de nfse vazio.")
    void obterCreditoPorNumeroNfseVazioTest() {
        List<Credito> results = creditoService.obterCreditoPorNumeroNfse("");
        assertThat(results).isNull();
    }

    @Test
    @DisplayName("Obter uma lista de credito vazio ao passar o numero de nfse incorreto.")
    void obterCreditoPorNumeroNfseIncorretoTest() {
        String numeroNfseIncorreto = String.format("sA%s&Z", this.credito.getNumeroNfse());
        List<Credito> results = creditoService.obterCreditoPorNumeroNfse(numeroNfseIncorreto);
        assertThat(results).isNotNull();
        assertThat(results).isEmpty();
    }

}