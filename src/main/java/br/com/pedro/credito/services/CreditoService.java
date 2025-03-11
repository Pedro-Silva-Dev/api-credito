package br.com.pedro.credito.services;


import br.com.pedro.credito.models.Credito;
import br.com.pedro.credito.repositories.CreditoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditoService {

    private final CreditoRepository creditoRepository;

    public List<Credito> obterCreditoPorNumeroNfse(String numeroNfse) {
        if(numeroNfse != null && !numeroNfse.trim().isEmpty() && !numeroNfse.trim().equals("null")) {
            return creditoRepository.findAllByNumeroNfse(numeroNfse);
        }
        return null;
    }

}
