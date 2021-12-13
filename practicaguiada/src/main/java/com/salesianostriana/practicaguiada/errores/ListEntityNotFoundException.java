package com.salesianostriana.practicaguiada.errores;

import javax.persistence.EntityNotFoundException;

public class ListEntityNotFoundException extends EntityNotFoundException {

    public ListEntityNotFoundException (Class clase){
        super(String.format("No se puede encontrar la lista de %s",clase.getName()));
    }
}
