package com.salesianostriana.practicaguiada.validacion.anotaciones;


import com.salesianostriana.practicaguiada.validacion.validadores.PastOrPresentDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PastOrPresentDateValidator.class)
@Documented
public @interface PastOrPresentDate {

    String message() default "La fecha de apertura debe ser igual o anterior a la fecha de registro";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};

    String fechaApertura();
    String fechaRegistro();
}
