package com.salesianostriana.practicaguiada.validacion.anotaciones;

import com.salesianostriana.practicaguiada.validacion.validadores.UniqueUbicacionValidator;
import org.springframework.validation.annotation.Validated;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = UniqueUbicacionValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UniqueUbicacion {

    String message() default "No puede haber 2 ubicaciones iguales";

    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};
}
