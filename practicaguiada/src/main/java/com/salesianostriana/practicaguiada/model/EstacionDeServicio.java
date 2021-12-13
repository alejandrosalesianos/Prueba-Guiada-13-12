package com.salesianostriana.practicaguiada.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class EstacionDeServicio {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private String marca;

    private String ubicacion;

    private boolean tieneAutolavado = false;

    private double precioGasoilNormal;

    private double precioGasolina95Octanos;

    private double precioGasoilEspecial;

    private double precioGasolina98;

    @Lob
    private String servicios;

    @Past
    private LocalDateTime fechaApertura;



}
