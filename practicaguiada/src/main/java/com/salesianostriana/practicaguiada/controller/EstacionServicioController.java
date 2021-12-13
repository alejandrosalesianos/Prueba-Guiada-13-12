package com.salesianostriana.practicaguiada.controller;

import com.salesianostriana.practicaguiada.Dto.CreateEstacionDto;
import com.salesianostriana.practicaguiada.model.EstacionDeServicio;
import com.salesianostriana.practicaguiada.Dto.EstacionDtoConverter;
import com.salesianostriana.practicaguiada.Dto.GetEstacionDto;
import com.salesianostriana.practicaguiada.services.EstacionServicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estacion")
public class EstacionServicioController {

    private final EstacionServicioService estacionServicioService;
    private final EstacionDtoConverter estacionDtoConverter;

    @GetMapping("/")
    public ResponseEntity<List<EstacionDeServicio>> findAll(){
            return ResponseEntity.ok().body(estacionServicioService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetEstacionDto> findOne(@PathVariable Long id){
        return ResponseEntity.ok().body(estacionDtoConverter.EstacionToGetEstacionDto(estacionServicioService.findById(id).get()));
    }
    @PostMapping("/")
    public ResponseEntity<GetEstacionDto> save(@Valid @RequestBody CreateEstacionDto estacionDeServicio){
        EstacionDeServicio estacion = estacionDtoConverter.createEstacionDtoToEstacion(estacionDeServicio);
        estacionServicioService.save(estacion);
        GetEstacionDto estacionDto = estacionDtoConverter.EstacionToGetEstacionDto(estacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(estacionDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        estacionServicioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Optional<GetEstacionDto>> edit(@PathVariable Long id, @Valid @RequestBody CreateEstacionDto estacionDeServicio) {
        return ResponseEntity.ok().body(estacionServicioService.findById(id).map(e ->
        {e.setId(id);
            e.setMarca(estacionDeServicio.getMarca());
            e.setNombre(estacionDeServicio.getNombre());
            e.setUbicacion(estacionDeServicio.getUbicacion());
            e.setTieneAutolavado(estacionDeServicio.isTieneAutolavado());
            e.setPrecioGasoilNormal(estacionDeServicio.getPrecioGasoilNormal());
            e.setPrecioGasoilEspecial(estacionDeServicio.getPrecioGasoilEspecial());
            e.setPrecioGasolina98(estacionDeServicio.getPrecioGasolina98());
            e.setPrecioGasolina95Octanos(estacionDeServicio.getPrecioGasolina95Octanos());
            e.setServicios(estacionDeServicio.getServicios());
            e.setFechaApertura(estacionDeServicio.getFechaApertura());
            estacionServicioService.save(e);
            GetEstacionDto dto = estacionDtoConverter.EstacionToGetEstacionDto(e);
                return dto;
        }));
        }
}
