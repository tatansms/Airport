package edu.unimagdalena.aereopuerto.services;

import edu.unimagdalena.aereopuerto.entities.Pasajero;

import java.util.List;
import java.util.Optional;

public interface PasajeroService {
    List<Pasajero> obtenerTodosOrdenadosPorNombre();

    Optional<Pasajero> obtenerPorId(Long id);

    Optional<Pasajero> obtenerPorNombre(String nombre);

    Pasajero guardar(Pasajero pasajero);

    void eliminar(Long id);

    Long contarPasajeros();

    List<Pasajero> obtenerUltimos5Pasajeros();

    List<Pasajero> obtenerPasajerosConReservas();

    List<Pasajero> obtenerPasajerosSinReservas();

    Optional<Pasajero> obtenerPasajeroConMasReservas();
}
