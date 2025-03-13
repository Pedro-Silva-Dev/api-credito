package br.com.pedro.credito.models.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LogDto(String uuid, String message, LocalDate dhc) {

}
