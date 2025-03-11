package br.com.pedro.credito.controllers;

import br.com.pedro.credito.exceptions.BadRequestException;
import br.com.pedro.credito.exceptions.NotFoundRequestException;
import br.com.pedro.credito.models.Credito;
import br.com.pedro.credito.models.dto.CreditoDto;
import br.com.pedro.credito.models.mapper.CreditoMapper;
import br.com.pedro.credito.services.CreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/creditos")
public class CreditoController {

    private final CreditoService creditoService;

    @GetMapping("/{numeroNfse}")
    public ResponseEntity<List<CreditoDto>> obterCreditosPorNfse(@PathVariable String numeroNfse) {
        List<Credito> creditos = creditoService.obterCreditosPorNumeroNfse(numeroNfse);
        if(creditos != null) {
            List<CreditoDto> creditoDtos = creditos.stream().map(CreditoMapper.INSTANCE::toCreditoDto).toList();
            return ResponseEntity.status(HttpStatus.OK.value()).body(creditoDtos);
        }
        throw new BadRequestException("Parametro incorreto, o numero nfse não pode ser nulo e não pode ser vázio.");
    }

    @GetMapping("/credito/{numeroCredito}")
    public ResponseEntity<CreditoDto> obterCreditoPorNumeroCredito(@PathVariable String numeroCredito) {
        Credito credito = creditoService.obterCreditoPorNumeroCredito(numeroCredito);
        if(credito != null) {
            CreditoDto creditoDto = CreditoMapper.INSTANCE.toCreditoDto(credito);
            return ResponseEntity.status(HttpStatus.OK.value()).body(creditoDto);
        }
        throw new NotFoundRequestException("Não foi possível encontrar o crédito, verifique numero e tente novamente.");
    }

}
