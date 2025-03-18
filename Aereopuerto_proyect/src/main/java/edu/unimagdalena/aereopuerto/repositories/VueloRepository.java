package edu.unimagdalena.aereopuerto.repositories;

import edu.unimagdalena.aereopuerto.entities.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface VueloRepository extends JpaRepository<Vuelo, Long> {
    Vuelo findVueloByNumeroVuelo(UUID numeroVuelo);
    List<Vuelo> findVuelosByOrigen(String origen);
    List<Vuelo> findVuelosByDestino(String destino);
    List<Vuelo> findVuelosByOrigenAndDestino(String origen, String destino);
    List<Vuelo> findVuelosByReservasIsNotNull();

    @Query("SELECT COUNT(v) FROM Vuelo v WHERE v.destino = ?1")
    Long countFlightsByDestination(String destino);
    @Query("SELECT v FROM Vuelo v WHERE v.destino LIKE %?1%")
    List<Vuelo> findVuelosByDestinoContaining(String cadena);
    @Query("SELECT v FROM Vuelo v WHERE v.origen LIKE %?1%")
    List<Vuelo> findVuelosByOrigenContaining(String cadena);
    @Query("SELECT v FROM Vuelo v WHERE v.destino = ?1 ORDER BY v.origen ASC")
    List<Vuelo> findVuelosByDestinoOrderByOrigen(String destino);
    @Query("SELECT COUNT(v) FROM Vuelo v WHERE v.origen = ?1")
    Long countVuelosByOrigen(String origen);
}