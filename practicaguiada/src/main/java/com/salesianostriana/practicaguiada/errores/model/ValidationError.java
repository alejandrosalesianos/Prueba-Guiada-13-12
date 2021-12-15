package com.salesianostriana.practicaguiada.errores.model;

import com.salesianostriana.practicaguiada.errores.model.ApiSubError;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ValidationError extends ApiSubError {

    private String objeto, campo,mensaje;
    private Object ValorRechazado;
}
