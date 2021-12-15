package com.salesianostriana.practicaguiada.Dto;

import com.salesianostriana.practicaguiada.model.EstacionDeServicio;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EstacionDtoConverter {

    public EstacionDeServicio createEstacionDtoToEstacion(CreateEstacionDto dto){
        return EstacionDeServicio.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .marca(dto.getMarca())
                .ubicacion(dto.getUbicacion())
                .tieneAutolavado(dto.isTieneAutolavado())
                .precioGasoilNormal(dto.getPrecioGasoilNormal())
                .precioGasoilEspecial(dto.getPrecioGasoilEspecial())
                .precioGasolina98(dto.getPrecioGasolina98())
                .precioGasolina95Octanos(dto.getPrecioGasolina95Octanos())
                .fechaApertura(dto.getFechaApertura())
                .servicios(dto.getServicios())
                .build();
    }
    public GetEstacionDto EstacionToGetEstacionDto(EstacionDeServicio estacionDeServicio){
        return GetEstacionDto.builder()
                .id(estacionDeServicio.getId())
                .nombre(estacionDeServicio.getNombre())
                .marca(estacionDeServicio.getMarca())
                .ubicacion(estacionDeServicio.getUbicacion())
                .tieneAutolavado(estacionDeServicio.isTieneAutolavado())
                .precioGasoilNormal(estacionDeServicio.getPrecioGasoilNormal())
                .precioGasoilEspecial(estacionDeServicio.getPrecioGasoilEspecial())
                .precioGasolina98(estacionDeServicio.getPrecioGasolina98())
                .precioGasolina95Octanos(estacionDeServicio.getPrecioGasolina95Octanos())
                .fechaApertura(estacionDeServicio.getFechaApertura())
                .servicios(estacionDeServicio.getServicios())
                .fechaRegistro(LocalDateTime.now())
                .build();
    }

}
