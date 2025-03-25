package edu.unimagdalena.aereopuerto.services;

import edu.unimagdalena.aereopuerto.entities.Pasajero;
import edu.unimagdalena.aereopuerto.repositories.PasajeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PasajeroServiceImp implements PasajeroService {

    private final PasajeroRepository pasajeroRepository;

    @Autowired
    public PasajeroServiceImp(PasajeroRepository pasajeroRepository) {
        this.pasajeroRepository = pasajeroRepository;
    }

    public List<Pasajero> obtenerTodosOrdenadosPorNombre() {
        return pasajeroRepository.findPasajerosAllByOrderByNombreAsc();
    }

    public Optional<Pasajero> obtenerPorId(Long id) {
        return Optional.ofNullable(pasajeroRepository.findPasajeroById(id));
    }

    public Optional<Pasajero> obtenerPorNombre(String nombre) {
        return Optional.ofNullable(pasajeroRepository.findPasajeroByNombre(nombre));
    }

    public Pasajero guardar(Pasajero pasajero) {
        return pasajeroRepository.save(pasajero);
    }

    public void eliminar(Long id) {
        pasajeroRepository.deleteById(id);
    }

    public Long contarPasajeros() {
        return pasajeroRepository.countPassengers();
    }

    public List<Pasajero> obtenerUltimos5Pasajeros() {
        return pasajeroRepository.getLast5Passengers();
    }

    public List<Pasajero> obtenerPasajerosConReservas() {
        return pasajeroRepository.getPassengersWithReservations();
    }

    public List<Pasajero> obtenerPasajerosSinReservas() {
        return pasajeroRepository.findPassengersWithoutReservations();
    }

    public Optional<Pasajero> obtenerPasajeroConMasReservas() {
        return Optional.ofNullable(pasajeroRepository.getPassengerWithMoreBookings());
    }
}
