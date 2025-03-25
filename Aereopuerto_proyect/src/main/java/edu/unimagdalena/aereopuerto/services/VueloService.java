package edu.unimagdalena.aereopuerto.services;

import edu.unimagdalena.aereopuerto.entities.Vuelo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VueloService {
    List<Vuelo> obtenerTodos();
    Optional<Vuelo> obtenerPorId(Long id);
    Optional<Vuelo> obtenerPorNumeroVuelo(UUID numeroVuelo);
    Vuelo guardar(Vuelo vuelo);
    void eliminar(Long id);
    List<Vuelo> buscarPorOrigen(String origen);
    List<Vuelo> buscarPorDestino(String destino);

}
