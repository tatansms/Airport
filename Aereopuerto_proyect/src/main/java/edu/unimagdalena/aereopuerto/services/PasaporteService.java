package edu.unimagdalena.aereopuerto.services;

import edu.unimagdalena.aereopuerto.entities.Pasaporte;

import java.util.List;
import java.util.Optional;

public interface PasaporteService {
    List<Pasaporte> obtenerTodos();
    Optional<Pasaporte> obtenerPorId(Long id);
    Pasaporte guardar(Pasaporte pasaporte);
    void eliminar(Long id);

}
