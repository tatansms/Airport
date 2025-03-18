package edu.unimagdalena.aereopuerto.repositories;

import edu.unimagdalena.aereopuerto.entities.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PasajeroRepository extends JpaRepository<Pasajero, Long> {
    List<Pasajero> findPasajerosAllByOrderByNombreAsc();
    Pasajero findPasajeroById(Long id);
    Pasajero findPasajeroByNombre(String nombre);
    List<Pasajero> findPasajerosByIdIn(List<Long> pasajeroIds);
    List<Pasajero> findPasajerosByNombreLike(String pasajeroNameLike);

    @Query("SELECT p FROM Pasajero p WHERE p.id = (SELECT r.pasajero.id FROM Reserva r GROUP BY r.pasajero.id ORDER BY COUNT(r.id) DESC LIMIT 1)")
    Pasajero getPassengerWithMoreBookings();
    @Query("SELECT COUNT(p) FROM Pasajero p")
    Long countPassengers();
    @Query("SELECT p FROM Pasajero p ORDER BY p.id DESC")
    List<Pasajero> getLast5Passengers();
    @Query("SELECT p FROM Pasajero p WHERE p.id IN (SELECT r.pasajero.id FROM Reserva r)")
    List<Pasajero> getPassengersWithReservations();
    @Query("SELECT p FROM Pasajero p WHERE p.id NOT IN (SELECT r.pasajero.id FROM Reserva r)")
    List<Pasajero> findPassengersWithoutReservations();
}

