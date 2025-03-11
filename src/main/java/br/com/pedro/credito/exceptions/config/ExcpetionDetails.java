package br.com.pedro.credito.exceptions.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ExcpetionDetails implements Serializable {
    protected String titulo;
    protected Integer status;
    protected String detalhes;
    protected LocalDateTime timestamp;
}