package com.salesianostriana.practicaguiada.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GetEstacionDto {
    private Long id;
    private String nombre;
    private String marca;
    private String ubicacion;
    private boolean tieneAutolavado;
    private double precioGasoilNormal;
    private double precioGasolina95Octanos;
    private double precioGasoilEspecial;
    private double precioGasolina98;
    private String servicios;
    private LocalDateTime fechaApertura;
    private LocalDateTime fechaRegistro;

}
