package edu.unimagdalena.aereopuerto.controllers;

import edu.unimagdalena.aereopuerto.services.PasaporteService;
import edu.unimagdalena.aereopuerto.entities.Pasaporte;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/pasaportes") // Ruta base del controlador
public class PasaporteController {

    private final PasaporteService pasaporteService;

    public PasaporteController(PasaporteService pasaporteService) {
        this.pasaporteService = pasaporteService;
    }

    @GetMapping("/{id}")
    public Optional<Pasaporte> obtenerPasaportePorId(@PathVariable Long id) {
        return pasaporteService.obtenerPorId(id);
    }
}
