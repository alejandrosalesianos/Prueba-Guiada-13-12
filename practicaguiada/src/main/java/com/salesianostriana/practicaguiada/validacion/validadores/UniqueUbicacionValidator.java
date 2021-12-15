package com.salesianostriana.practicaguiada.validacion.validadores;

import com.salesianostriana.practicaguiada.repository.EstacionServicioRepository;
import com.salesianostriana.practicaguiada.services.EstacionServicioService;
import com.salesianostriana.practicaguiada.validacion.anotaciones.UniqueUbicacion;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueUbicacionValidator implements ConstraintValidator<UniqueUbicacion, String> {

    private final EstacionServicioRepository estacionServicioRepository;

    @Override
    public void initialize(UniqueUbicacion constraintAnnotation) {
    }

    @Override
    public boolean isValid(String ubicacion, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.hasText(ubicacion) && !estacionServicioRepository.existsByUbicacion(ubicacion);
    }

}
