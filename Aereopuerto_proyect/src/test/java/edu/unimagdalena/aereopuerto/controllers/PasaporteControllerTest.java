package edu.unimagdalena.aereopuerto.controllers;

import edu.unimagdalena.aereopuerto.entities.Pasajero;
import edu.unimagdalena.aereopuerto.entities.Pasaporte;
import edu.unimagdalena.aereopuerto.services.PasaporteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PasaporteController.class) // Prueba solo el controlador
class PasaporteControllerTest {

    @Autowired
    private MockMvc mockMvc; // Simula llamadas HTTP al controlador

    @MockBean
    private PasaporteService pasaporteService; // Simula la lógica de negocio

    @Test
    void obtenerPasaportePorId_Existe() throws Exception {
        // Simulamos un pasaporte con ID 1
        Pasajero pasajero = new Pasajero();
        Pasaporte pasaporte = new Pasaporte(1L, "Juan Pérez", pasajero);
        Mockito.when(pasaporteService.obtenerPorId(1L)).thenReturn(Optional.of(pasaporte));

        mockMvc.perform(get("/api/pasaportes/1") // Simula GET /api/pasaportes/1
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Espera 200 OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Juan Pérez"))
                .andExpect(jsonPath("$.numero").value("123456789"));
    }

    @Test
    void obtenerPasaportePorId_NoExiste() throws Exception {
        Mockito.when(pasaporteService.obtenerPorId(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/pasaportes/99") // Simula GET /api/pasaportes/99
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()); // Espera 404 Not Found
    }
}
