package com.salesianostriana.practicaguiada.errores;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ValidationError extends ApiSubError{

    private String objeto, campo,mensaje;
    private Object ValorRechazado;
}
