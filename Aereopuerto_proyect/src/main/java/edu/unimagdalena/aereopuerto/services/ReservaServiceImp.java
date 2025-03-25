package edu.unimagdalena.aereopuerto.services;

import edu.unimagdalena.aereopuerto.entities.Reserva;
import edu.unimagdalena.aereopuerto.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReservaServiceImp implements ReservaService{

    private final ReservaRepository reservaRepository;

    @Autowired
    public ReservaServiceImp(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<Reserva> obtenerTodas() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> obtenerPorId(Long id) {
        return Optional.ofNullable(reservaRepository.findReservaById(id));
    }

    public Optional<Reserva> obtenerPorCodigoReserva(UUID codigo) {
        return Optional.ofNullable(reservaRepository.findByCodigoReserva(codigo));
    }

    public Reserva guardar(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public void eliminar(Long id) {
        reservaRepository.deleteById(id);
    }

    public Long contarReservasPorPasajero(String nombrePasajero) {
        return reservaRepository.countReservasByPasajeroNombre(nombrePasajero);
    }

    public List<Reserva> obtenerReservasPorPasajero(Long pasajeroId) {
        return reservaRepository.findReservasByPasajeroId(pasajeroId);
    }

    public List<Reserva> obtenerReservasPorVuelo(Long vueloId) {
        return reservaRepository.findReservasByVueloId(vueloId);
    }

    public List<Reserva> obtenerReservasPorOrigenYDestino(String origen, String destino) {
        return reservaRepository.findReservasByOrigenAndDestino(origen, destino);
    }

    public List<Reserva> obtenerReservasPorCiudadOrigen(String ciudad) {
        return reservaRepository.findReservasByCiudadOrigen(ciudad);
    }

    public List<Reserva> obtenerReservasPorDestinoVuelo(String destino) {
        return reservaRepository.findReservationsByFlightDestination(destino);
    }

    public List<Reserva> obtenerReservasRecientes() {
        return reservaRepository.findRecentReservations();
    }
}
