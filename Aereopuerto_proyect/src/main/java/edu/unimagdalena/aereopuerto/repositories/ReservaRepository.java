package edu.unimagdalena.aereopuerto.repositories;

import edu.unimagdalena.aereopuerto.entities.Reserva;
import edu.unimagdalena.aereopuerto.entities.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    Long countReservasByPasajeroNombre(String nombrePasajero);
    Reserva findReservaById(Long id);
    List<Reserva> findReservasByPasajeroId(Long pasajeroId);
    Reserva findByCodigoReserva(UUID codigoReserva);
    List<Reserva> findReservasByVueloId(Long vueloId);

    @Query("SELECT r FROM Reserva r WHERE r.vuelo.origen = ?1 AND r.vuelo.destino = ?2")
    List<Reserva> findReservasByOrigenAndDestino(String origen, String destino);
    @Query("SELECT r FROM Reserva r WHERE r.vuelo.origen = ?1")
    List<Reserva> findReservasByCiudadOrigen(String ciudadOrigen);
    @Query("SELECT r FROM Reserva r WHERE r.vuelo.id IN (SELECT v.id FROM Vuelo v WHERE v.destino = ?1)")
    List<Reserva> findReservationsByFlightDestination(String destino);
    @Query("SELECT r FROM Reserva r WHERE r.vuelo.id = ?1")
    List<Reserva> findReservasByCodigoVuelo(Long id);
    @Query("SELECT r FROM Reserva r ORDER BY r.id DESC")
    List<Reserva> findRecentReservations();
}

