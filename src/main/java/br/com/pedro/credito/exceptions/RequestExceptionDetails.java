package br.com.pedro.credito.exceptions;

import br.com.pedro.credito.exceptions.config.ExcpetionDetails;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@SuperBuilder
public class RequestExceptionDetails extends ExcpetionDetails implements Serializable {

}
