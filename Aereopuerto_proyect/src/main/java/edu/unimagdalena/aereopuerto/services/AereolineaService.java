package edu.unimagdalena.aereopuerto.services;

import edu.unimagdalena.aereopuerto.entities.Aereolinea;
import edu.unimagdalena.aereopuerto.repositories.AereolineaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AereolineaService {

    private final AereolineaRepository aereolineaRepository;

    @Autowired
    public AereolineaService(AereolineaRepository aereolineaRepository) {
        this.aereolineaRepository = aereolineaRepository;
    }

    public List<Aereolinea> obtenerTodas() {
        return aereolineaRepository.findAll();
    }

    public Optional<Aereolinea> obtenerPorId(Long id) {
        return aereolineaRepository.findById(id);
    }

    public Aereolinea guardar(Aereolinea aereolinea) {
        return aereolineaRepository.save(aereolinea);
    }

    public void eliminar(Long id) {
        aereolineaRepository.deleteById(id);
    }

    public List<Aereolinea> buscarPorNombreQueEmpieceCon(String letra) {
        return aereolineaRepository.findAereolineaByNombreStartingWith(letra);
    }

    public Optional<Aereolinea> obtenerPorNombre(String nombre) {
        return Optional.ofNullable(aereolineaRepository.findAereolineaByNombre(nombre));
    }

    public List<Aereolinea> obtenerAereolineasConVuelos() {
        return aereolineaRepository.getAirlinesWithFlights();
    }

    public List<Aereolinea> obtenerAereolineasPorDestino(String destino) {
        return aereolineaRepository.getAirlinesByDestination(destino);
    }

    public List<Aereolinea> obtenerAereolineasSinVuelos() {
        return aereolineaRepository.getAirlinesWithoutFlights();
    }

    public Long contarAereolineas() {
        return aereolineaRepository.countAirlines();
    }
}
