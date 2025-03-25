package edu.unimagdalena.aereopuerto.services;

import edu.unimagdalena.aereopuerto.entities.Aereolinea;

import java.util.List;
import java.util.Optional;

public interface AereolineaService {
    List<Aereolinea> obtenerTodas();
    Optional<Aereolinea> obtenerPorId(Long id);
    Aereolinea guardar(Aereolinea aereolinea);
    void eliminar(Long id);
    List<Aereolinea> buscarPorNombreQueEmpieceCon(String letra);
    Optional<Aereolinea> obtenerPorNombre(String nombre);
    List<Aereolinea> obtenerAereolineasConVuelos();
    List<Aereolinea> obtenerAereolineasPorDestino(String destino);
    List<Aereolinea> obtenerAereolineasSinVuelos();
    Long contarAereolineas();

    
}
