package com.salesianostriana.practicaguiada.services;

import com.salesianostriana.practicaguiada.errores.EstacionNotFoundException;
import com.salesianostriana.practicaguiada.errores.ListEntityNotFoundException;
import com.salesianostriana.practicaguiada.model.EstacionDeServicio;
import com.salesianostriana.practicaguiada.repository.EstacionServicioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstacionServicioService {

    private final EstacionServicioRepository repository;

    public List<EstacionDeServicio> findAll() {
        if (repository.findAll().isEmpty()) {
            throw new ListEntityNotFoundException(EstacionDeServicio.class);
        }
        else {
            return this.repository.findAll();
        }
    }

    public Optional<EstacionDeServicio> findById(Long id) {
        if (repository.findById(id).isEmpty()){
            throw new EstacionNotFoundException(id);
        }else{
            return this.repository.findById(id);
        }
    }

    public EstacionDeServicio save(EstacionDeServicio estacionDeServicio) {
        return repository.save(estacionDeServicio);
    }

    public EstacionDeServicio edit(EstacionDeServicio estacionDeServicio) {

        return repository.save(estacionDeServicio);
    }

    public void deleteById(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new EstacionNotFoundException(id);
        } else {
            repository.deleteById(id);
        }
    }
}
