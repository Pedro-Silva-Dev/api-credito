package br.com.pedro.credito.repositories;

import br.com.pedro.credito.models.Credito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditoRepository extends JpaRepository<Credito, Long> {

    List<Credito> findAllByNumeroNfse(String numeroNfse);

    @Query(value = "SELECT c.* FROM credito c WHERE c.numero_credito =?1 LIMIT 1", nativeQuery = true)
    Optional<Credito> findOneByNumeroCredito(String numeroCredito);
}
