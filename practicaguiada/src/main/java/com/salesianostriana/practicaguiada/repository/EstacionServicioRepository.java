package com.salesianostriana.practicaguiada.repository;

import com.salesianostriana.practicaguiada.model.EstacionDeServicio;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EstacionServicioRepository extends JpaRepository<EstacionDeServicio, Long> {

    boolean existsByUbicacion(String ubicacion);
}
