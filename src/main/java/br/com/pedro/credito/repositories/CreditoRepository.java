package br.com.pedro.credito.repositories;

import br.com.pedro.credito.models.Credito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditoRepository extends JpaRepository<Credito, Long> {
    
}
