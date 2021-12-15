package com.salesianostriana.practicaguiada.errores.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.salesianostriana.practicaguiada.errores.model.ApiSubError;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ValidationError extends ApiSubError {

    private String objeto, mensaje;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String campo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object ValorRechazado;
}
