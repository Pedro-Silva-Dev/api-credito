package br.com.pedro.credito.repositories;

import br.com.pedro.credito.models.Credito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditoRepository extends JpaRepository<Credito, Long> {

    @Query(value = "SELECT * FROM credito c ORDER BY c.id DESC LIMIT 1", nativeQuery = true)
    Optional<Credito> findOneByLastId();

    List<Credito> findAllByNumeroNfse(String numeroNfse);

}
