package edu.unimagdalena.aereopuerto.repositories;

import edu.unimagdalena.aereopuerto.entities.Aereolinea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AereolineaRepository extends JpaRepository<Aereolinea, Long> {
    List<Aereolinea> findAereolineaByNombreStartingWith(String letra);
    Aereolinea findAereolineaByIdAereolinea(Long idAerolinea);
    Aereolinea findAereolineaByNombre(String nombre);
    List <Aereolinea> findAereolineaByIdAereolineaIn(List<Long> ids);
    List <Aereolinea> findAereolineasByNombreLike(String nombre);

    @Query("SELECT DISTINCT a FROM Aereolinea a JOIN a.vuelos v WHERE v IS NOT NULL")
    List<Aereolinea> getAirlinesWithFlights();
    @Query("SELECT DISTINCT a FROM Aereolinea a JOIN a.vuelos v WHERE v.destino = ?1")
    List<Aereolinea> getAirlinesByDestination(String destino);
    @Query("SELECT a FROM Aereolinea a JOIN a.vuelos v GROUP BY a ORDER BY COUNT(v) DESC LIMIT 1")
    Aereolinea getAirlineWithMoreFlights();
    @Query("SELECT a FROM Aereolinea a WHERE a.idAereolinea NOT IN (SELECT DISTINCT a2.idAereolinea FROM Aereolinea a2 JOIN a2.vuelos v)")
    List<Aereolinea> getAirlinesWithoutFlights();
    @Query("SELECT COUNT(a) FROM Aereolinea a")
    Long countAirlines();
}