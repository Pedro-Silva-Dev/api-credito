package br.com.pedro.credito.exceptions.config;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExcpetionDetails implements Serializable {
    protected String titulo;
    protected Integer status;
    protected String detalhes;
    protected LocalDateTime timestamp;
}