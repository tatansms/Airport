package edu.unimagdalena.aereopuerto.services;


import edu.unimagdalena.aereopuerto.entities.Pasaporte;
import edu.unimagdalena.aereopuerto.repositories.PasaporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PasaporteServiceImp implements PasaporteService {

    private final PasaporteRepository pasaporteRepository;

    @Autowired
    public PasaporteServiceImp(PasaporteRepository pasaporteRepository) {
        this.pasaporteRepository = pasaporteRepository;
    }

    public List<Pasaporte> obtenerTodos() {
        return pasaporteRepository.findAll();
    }

    public Optional<Pasaporte> obtenerPorId(Long id) {
        return pasaporteRepository.findById(id);
    }

    public Pasaporte guardar(Pasaporte pasaporte) {
        return pasaporteRepository.save(pasaporte);
    }

    public void eliminar(Long id) {
        pasaporteRepository.deleteById(id);
    }
}
