package edu.unimagdalena.aereopuerto.services;

import edu.unimagdalena.aereopuerto.entities.Aereolinea;
import edu.unimagdalena.aereopuerto.repositories.AereolineaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AereolineaServiceTest {

    @Mock
    private AereolineaRepository aereolineaRepository;

    @InjectMocks
    private AereolineaService aereolineaService;

    private Aereolinea aereolinea;

    @BeforeEach
    void setUp() {
        aereolinea = new Aereolinea(1L, "Avianca", null);
    }

    @Test
    void obtenerTodas() {
        when(aereolineaRepository.findAll()).thenReturn(Arrays.asList(aereolinea));

        List<Aereolinea> resultado = aereolineaService.obtenerTodas();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(aereolineaRepository, times(1)).findAll();
    }

    @Test
    void obtenerPorId() {
        when(aereolineaRepository.findById(1L)).thenReturn(Optional.of(aereolinea));

        Optional<Aereolinea> resultado = aereolineaService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Avianca", resultado.get().getNombre());
        verify(aereolineaRepository, times(1)).findById(1L);
    }

    @Test
    void guardar() {
        when(aereolineaRepository.save(any(Aereolinea.class))).thenReturn(aereolinea);

        Aereolinea resultado = aereolineaService.guardar(aereolinea);

        assertNotNull(resultado);
        assertEquals("Avianca", resultado.getNombre());
        verify(aereolineaRepository, times(1)).save(aereolinea);
    }

    @Test
    void eliminar() {
        doNothing().when(aereolineaRepository).deleteById(1L);

        aereolineaService.eliminar(1L);

        verify(aereolineaRepository, times(1)).deleteById(1L);
    }

    @Test
    void buscarPorNombreQueEmpieceCon() {
        when(aereolineaRepository.findAereolineaByNombreStartingWith("A"))
                .thenReturn(Arrays.asList(aereolinea));

        List<Aereolinea> resultado = aereolineaService.buscarPorNombreQueEmpieceCon("A");

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(aereolineaRepository, times(1)).findAereolineaByNombreStartingWith("A");
    }

    @Test
    void obtenerPorNombre() {
        when(aereolineaRepository.findAereolineaByNombre("Avianca")).thenReturn(aereolinea);

        Optional<Aereolinea> resultado = aereolineaService.obtenerPorNombre("Avianca");

        assertTrue(resultado.isPresent());
        assertEquals("Avianca", resultado.get().getNombre());
        verify(aereolineaRepository, times(1)).findAereolineaByNombre("Avianca");
    }

    @Test
    void obtenerAereolineasConVuelos() {
        when(aereolineaRepository.getAirlinesWithFlights()).thenReturn(Arrays.asList(aereolinea));

        List<Aereolinea> resultado = aereolineaService.obtenerAereolineasConVuelos();

        assertFalse(resultado.isEmpty());
        verify(aereolineaRepository, times(1)).getAirlinesWithFlights();
    }

    @Test
    void obtenerAereolineasPorDestino() {
        when(aereolineaRepository.getAirlinesByDestination("Medellin")).thenReturn(Arrays.asList(aereolinea));

        List<Aereolinea> resultado = aereolineaService.obtenerAereolineasPorDestino("Medellin");

        assertFalse(resultado.isEmpty());
        verify(aereolineaRepository, times(1)).getAirlinesByDestination("Medellin");
    }

    @Test
    void obtenerAereolineasSinVuelos() {
        when(aereolineaRepository.getAirlinesWithoutFlights()).thenReturn(Arrays.asList(aereolinea));

        List<Aereolinea> resultado = aereolineaService.obtenerAereolineasSinVuelos();

        assertFalse(resultado.isEmpty());
        verify(aereolineaRepository, times(1)).getAirlinesWithoutFlights();
    }

    @Test
    void contarAereolineas() {
        when(aereolineaRepository.countAirlines()).thenReturn(5L);

        Long resultado = aereolineaService.contarAereolineas();

        assertEquals(5L, resultado);
        verify(aereolineaRepository, times(1)).countAirlines();
    }
}
