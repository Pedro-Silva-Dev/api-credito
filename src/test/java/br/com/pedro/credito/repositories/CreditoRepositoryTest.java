package br.com.pedro.credito.repositories;

import br.com.pedro.credito.models.Credito;
import br.com.pedro.credito.util.CreditoUtilTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DisplayName("Testar o repositorio de Credito.")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreditoRepositoryTest {

    @Autowired
    private CreditoRepository creditoRepository;

    private Credito credito;

    @Container
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:17.4-alpine"))
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test")
            .waitingFor(Wait.forListeningPort());


    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create");
    }

    void cadastrarCredito() {
        Credito novoCredito = CreditoUtilTest.obterCreditoParaCadastro();
        credito = creditoRepository.save(novoCredito);
    }

    @DisplayName("Criar um crédito antes de cada teste.")
    @BeforeEach
    void cadastrarCreditoBefore() {
        this.cadastrarCredito();
    }

    @DisplayName("Remover todas os creditos depois de cada teste.")
    @AfterEach
    void removerCreditosAfter() {
        creditoRepository.deleteAll();
    }

    @Test
    @DisplayName("Obter uma lista de crédito por número de nfse.")
    void findAllByNumeroNfseTest() {
        String numeroNfse = credito.getNumeroNfse();
        List<Credito> results = creditoRepository.findAllByNumeroNfse(numeroNfse);
        assertThat(results).isNotEmpty();
        assertThat(results.get(0)).isNotNull();
        assertThat(results.get(0).getNumeroNfse()).isNotNull();
        assertThat(results.get(0).getNumeroNfse()).isEqualTo(numeroNfse);
    }

    @Test
    @DisplayName("Obter lista de crédito vazia ao passar o número de nfse inválido.")
    void findAllByNumeroNfseInvalidoTest() {
        List<Credito> results = creditoRepository.findAllByNumeroNfse(null);
        assertThat(results).isNotNull();
        assertThat(results).isEmpty();
    }

    @Test
    @DisplayName("Obter lista de crédito vazia ao passar o número de nfse vazio.")
    void findAllByNumeroNfseVazioTest() {
        List<Credito> results = creditoRepository.findAllByNumeroNfse("");
        assertThat(results).isNotNull();
        assertThat(results).isEmpty();
    }

    @Test
    @DisplayName("Obter lista de crédito vazia ao passar o número de nfse incorreto.")
    void findAllByNumeroNfseIncorretoTest() {
        String numeroNfseIncorreto = String.format("A@$%sD#", credito.getNumeroNfse());
        List<Credito> results = creditoRepository.findAllByNumeroNfse(numeroNfseIncorreto);
        assertThat(results).isNotNull();
        assertThat(results).isEmpty();
    }

    @Test
    @DisplayName("Obter um crédito por número de crédito.")
    void findOneByNumeroCreditoTest() {
        String numeroCredito = credito.getNumeroCredito();
        Optional<Credito> result = creditoRepository.findOneByNumeroCredito(numeroCredito);
        assertThat(result).isNotNull();
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isNotNull();
        assertThat(result.get().getNumeroCredito()).isEqualTo(numeroCredito);
    }

    @Test
    @DisplayName("Obter um crédito vazia ao passar o número de crédito inválido.")
    void findOneByNumeroCreditoInvalidoTest() {
        Optional<Credito> result = creditoRepository.findOneByNumeroCredito(null);
        assertThat(result).isNotNull();
        assertThat(result.isPresent()).isFalse();
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Obter um crédito vazia ao passar o número de crédito vazio.")
    void findOneByNumeroCreditoVazioTest() {
        Optional<Credito> result = creditoRepository.findOneByNumeroCredito("");
        assertThat(result).isNotNull();
        assertThat(result.isPresent()).isFalse();
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Obter um crédito vazia ao passar o número de crédito incorreto.")
    void findOneByNumeroCreditoIncorretoTest() {
        String numeroCreditoIncorreto = String.format("A@$%sD#", credito.getNumeroCredito());
        Optional<Credito> result = creditoRepository.findOneByNumeroCredito(numeroCreditoIncorreto);
        assertThat(result).isNotNull();
        assertThat(result.isPresent()).isFalse();
        assertThat(result.isEmpty()).isTrue();
    }



}