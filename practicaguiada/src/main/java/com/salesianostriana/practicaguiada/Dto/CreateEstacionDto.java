package com.salesianostriana.practicaguiada.Dto;

import com.salesianostriana.practicaguiada.validacion.anotaciones.UniqueUbicacion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor @AllArgsConstructor
public class CreateEstacionDto {

    private Long id;
    @NotBlank(message = "{estaciondeservicio.nombre.blank}")
    private String nombre;

    @NotNull
    private String marca;

    @NotBlank(message = "{estaciondeservicio.ubicacion.blank}")
    @UniqueUbicacion
    @Pattern(regexp = "^([-+]?\\d{1,2}[.]\\d+),\\s*([-+]?\\d{1,3}[.]\\d+)$", message = "{estaciondeservicio.ubicacion.pattern}")
    private String ubicacion;

    private boolean tieneAutolavado = false;

    @PositiveOrZero(message = "{estaciondeservicio.preciogasoilnormal.positiveorzero}")
    private double precioGasoilNormal;

    @PositiveOrZero(message = "{estaciondeservicio.preciogasolina95octanos.positiveorzero}")
    private double precioGasolina95Octanos;

    @PositiveOrZero(message = "{estaciondeservicio.preciogasoilespecial.positiveorzero}")
    private double precioGasoilEspecial;

    @PositiveOrZero(message = "{estaciondeservicio.preciogasolina98.positiveorzero}")
    private double precioGasolina98;

    @Lob
    private String servicios;

    @Past
    private LocalDateTime fechaApertura;
}
