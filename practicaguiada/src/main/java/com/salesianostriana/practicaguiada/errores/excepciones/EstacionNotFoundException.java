package com.salesianostriana.practicaguiada.errores.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EstacionNotFoundException extends RuntimeException{

    public EstacionNotFoundException (Long id){
        super("No se pudo encontrar la estación con la id:" + id);
    }
}
