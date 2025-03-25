package edu.unimagdalena.aereopuerto.services;

import edu.unimagdalena.aereopuerto.entities.Reserva;

import java.rmi.server.UID;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservaService {

    List<Reserva> obtenerTodas();
    Optional<Reserva> obtenerPorId(Long id);
    Optional<Reserva> obtenerPorCodigoReserva(UUID codigo);
    Reserva guardar (Reserva reserva);
    void eliminar(Long id);
    Long contarReservasPorPasajero(String nombrePasajero);
    List<Reserva> obtenerReservasPorPasajero(Long pasajeroId);
    List<Reserva> obtenerReservasPorVuelo(Long vueloId);
    List<Reserva> obtenerReservasPorOrigenYDestino(String origen, String destino);
    List<Reserva> obtenerReservasPorCiudadOrigen(String ciudad);
    List<Reserva> obtenerReservasPorDestinoVuelo(String destino);
    List<Reserva> obtenerReservasRecientes();

}
