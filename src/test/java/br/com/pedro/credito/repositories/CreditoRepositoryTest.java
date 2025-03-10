package br.com.pedro.credito.repositories;

import br.com.pedro.credito.models.Credito;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
        Credito novoCredito = Credito.builder()
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
        credito = creditoRepository.save(novoCredito);
    }

    @DisplayName("Criar um credito antes de cada teste.")
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
    @DisplayName("Obter uma lista de credito por numero de nfse.")
    void findAllByNumeroNfseTest() {
        String numeroNfse = credito.getNumeroNfse();
        List<Credito> results = creditoRepository.findAllByNumeroNfse(numeroNfse);
        assertThat(results).isNotEmpty();
        assertThat(results.get(0)).isNotNull();
        assertThat(results.get(0).getNumeroNfse()).isNotNull();
        assertThat(results.get(0).getNumeroNfse()).isEqualTo(numeroNfse);
    }

    @Test
    @DisplayName("Obter lista de credito vazia ao passar o numero de nfse invalido.")
    void findAllByNumeroNfseInvalidoTest() {
        List<Credito> results = creditoRepository.findAllByNumeroNfse(null);
        assertThat(results).isNotNull();
        assertThat(results).isEmpty();
    }

    @Test
    @DisplayName("Obter lista de credito vazia ao passar o numero de nfse vazio.")
    void findAllByNumeroNfseVazioTest() {
        List<Credito> results = creditoRepository.findAllByNumeroNfse("");
        assertThat(results).isNotNull();
        assertThat(results).isEmpty();
    }

    @Test
    @DisplayName("Obter lista de credito vazia ao passar o numero de nfse incorreto.")
    void findAllByNumeroNfseIncorretoTest() {
        String numeroNfseIncorreto = String.format("A@$%sD#", credito.getNumeroNfse());
        List<Credito> results = creditoRepository.findAllByNumeroNfse(numeroNfseIncorreto);
        assertThat(results).isNotNull();
        assertThat(results).isEmpty();
    }

}