package edu.unimagdalena.aereopuerto.services;

import edu.unimagdalena.aereopuerto.entities.Vuelo;
import edu.unimagdalena.aereopuerto.repositories.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VueloService {

    private final VueloRepository vueloRepository;

    @Autowired
    public VueloService(VueloRepository vueloRepository) {
        this.vueloRepository = vueloRepository;
    }

    public List<Vuelo> obtenerTodos() {
        return vueloRepository.findAll();
    }

    public Optional<Vuelo> obtenerPorId(Long id) {
        return vueloRepository.findById(id);
    }

    public Optional<Vuelo> obtenerPorNumeroVuelo(UUID numeroVuelo) {
        return Optional.ofNullable(vueloRepository.findVueloByNumeroVuelo(numeroVuelo));
    }

    public Vuelo guardar(Vuelo vuelo) {
        return vueloRepository.save(vuelo);
    }

    public void eliminar(Long id) {
        vueloRepository.deleteById(id);
    }

    public List<Vuelo> buscarPorOrigen(String origen) {
        return vueloRepository.findVuelosByOrigen(origen);
    }

    public List<Vuelo> buscarPorDestino(String destino) {
        return vueloRepository.findVuelosByDestino(destino);
    }
}
