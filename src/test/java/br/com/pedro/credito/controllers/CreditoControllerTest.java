package br.com.pedro.credito.controllers;

import br.com.pedro.credito.exceptions.config.ExcpetionDetails;
import br.com.pedro.credito.models.Credito;
import br.com.pedro.credito.models.dto.CreditoDto;
import br.com.pedro.credito.repositories.CreditoRepository;
import br.com.pedro.credito.util.CreditoUtilTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DisplayName("Testar o controller de Credito.")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreditoControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

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
    @DisplayName("Obter uma lista de creditos pelo numero nfse.")
    void obterCreditosPorNfseTest() {
        String numeroNfse = this.credito.getNumeroNfse();
        String url = String.format("/api/creditos/%s", numeroNfse);
        ResponseEntity<List<CreditoDto>> result = testRestTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<CreditoDto>>() {});

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody()).isNotEmpty();
        assertThat(result.getBody().get(0).getNumeroNfse()).isEqualTo(numeroNfse);
    }

    @Test
    @DisplayName("Obter uma lista de creditos pelo numero nfse nulo.")
    void obterCreditosPorNfseNuloTest() {
        String numeroNfse = null;
        String url = String.format("/api/creditos/%s", numeroNfse);
        ResponseEntity<ExcpetionDetails> result = testRestTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<ExcpetionDetails>() {});

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Obter uma lista de creditos pelo numero nfse inválido.")
    void obterCreditosPorNfseInvalidoTest() {
        String numeroNfse = "null   ";
        String url = String.format("/api/creditos/%s", numeroNfse);
        ResponseEntity<ExcpetionDetails> result = testRestTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<ExcpetionDetails>() {});

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Obter uma lista de creditos pelo numero nfse incorreto.")
    void obterCreditosPorNfseIncorretoTest() {
        String numeroNfse = String.format("$a%s#Z", this.credito.getNumeroNfse());
        String url = String.format("/api/creditos/%s", numeroNfse);
        ResponseEntity<List<CreditoDto>> result = testRestTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<CreditoDto>>() {});

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody()).isEmpty();
    }

}