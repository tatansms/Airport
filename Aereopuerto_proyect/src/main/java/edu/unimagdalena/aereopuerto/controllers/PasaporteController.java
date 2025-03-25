package edu.unimagdalena.aereopuerto.controllers;

import edu.unimagdalena.aereopuerto.services.PasaporteServiceImp;
import edu.unimagdalena.aereopuerto.entities.Pasaporte;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/pasaportes") // Ruta base del controlador
public class PasaporteController {

    private final PasaporteServiceImp pasaporteServiceImp;

    public PasaporteController(PasaporteServiceImp pasaporteServiceImp) {
        this.pasaporteServiceImp = pasaporteServiceImp;
    }

    @GetMapping("/{id}")
    public Optional<Pasaporte> obtenerPasaportePorId(@PathVariable Long id) {
        return pasaporteServiceImp.obtenerPorId(id);
    }
}
