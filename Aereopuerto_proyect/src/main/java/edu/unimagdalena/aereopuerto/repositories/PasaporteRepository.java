package edu.unimagdalena.aereopuerto.repositories;

import edu.unimagdalena.aereopuerto.entities.Pasaporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PasaporteRepository extends JpaRepository<Pasaporte, Long> {
    Pasaporte findPasaporteByNumero(String numero);
    Pasaporte findPasaporteById(Long id);
    List<Pasaporte> findPasaportesByNumeroContaining(String fragmento);
    List<Pasaporte> findPasaportesByIdIn(List<Long> ids);
    List<Pasaporte> findPasaportesByNumeroStartingWith(String prefijo);

    @Query("SELECT p FROM Pasaporte p ORDER BY LENGTH(p.numero) DESC LIMIT 1")
    Pasaporte findPassportWithLongestNumber();
    @Query("SELECT p FROM Pasaporte p ORDER BY p.id ASC")
    List<Pasaporte> getAllOrderedById();
    @Query("SELECT p FROM Pasaporte p WHERE p.id BETWEEN ?1 AND ?2")
    List<Pasaporte> getPassportsInIdRange(Long inicio, Long fin);
    @Query("SELECT p FROM Pasaporte p ORDER BY p.id DESC LIMIT 1")
    Pasaporte getLastRegisteredPassport();
    @Query("SELECT COUNT(p) FROM Pasaporte p")
    Long countPassports();
}
