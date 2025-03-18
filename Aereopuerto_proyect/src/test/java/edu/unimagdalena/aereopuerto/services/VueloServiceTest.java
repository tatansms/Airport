package edu.unimagdalena.aereopuerto.services;

import edu.unimagdalena.aereopuerto.entities.Vuelo;
import edu.unimagdalena.aereopuerto.repositories.VueloRepository;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VueloServiceTest {

    @Mock
    private VueloRepository vueloRepository;

    @InjectMocks
    private VueloService vueloService;

    private Vuelo vuelo;

    @BeforeEach
    void setUp() {
        vuelo = new Vuelo(1L, UUID.randomUUID(), "Cali", "Santa Marta", null, null);
    }

    @Test
    void obtenerTodos() {
        when(vueloRepository.findAll()).thenReturn(Arrays.asList(vuelo));

        List<Vuelo> resultado = vueloService.obtenerTodos();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(vueloRepository, times(1)).findAll();
    }

    @Test
    void obtenerPorId() {
        when(vueloRepository.findById(1L)).thenReturn(Optional.of(vuelo));

        Optional<Vuelo> resultado = vueloService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Cali", resultado.get().getOrigen());
        verify(vueloRepository, times(1)).findById(1L);
    }

    @Test
    void obtenerPorNumeroVuelo() {
        when(vueloRepository.findVueloByNumeroVuelo(vuelo.getNumeroVuelo())).thenReturn(vuelo);

        Optional<Vuelo> resultado = vueloService.obtenerPorNumeroVuelo(vuelo.getNumeroVuelo());

        assertTrue(resultado.isPresent());
        assertEquals("Santa Marta", resultado.get().getDestino());
        verify(vueloRepository, times(1)).findVueloByNumeroVuelo(vuelo.getNumeroVuelo());
    }

    @Test
    void guardar() {
        when(vueloRepository.save(any(Vuelo.class))).thenReturn(vuelo);

        Vuelo resultado = vueloService.guardar(vuelo);

        assertNotNull(resultado);
        assertEquals("Cali", resultado.getOrigen());
        verify(vueloRepository, times(1)).save(vuelo);
    }

    @Test
    void eliminar() {
        doNothing().when(vueloRepository).deleteById(1L);

        vueloService.eliminar(1L);

        verify(vueloRepository, times(1)).deleteById(1L);
    }

    @Test
    void buscarPorOrigen() {
        when(vueloRepository.findVuelosByOrigen("Cali")).thenReturn(Arrays.asList(vuelo));

        List<Vuelo> resultado = vueloService.buscarPorOrigen("Cali");

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(vueloRepository, times(1)).findVuelosByOrigen("Cali");
    }

    @Test
    void buscarPorDestino() {
        when(vueloRepository.findVuelosByDestino("Santa Marta")).thenReturn(Arrays.asList(vuelo));

        List<Vuelo> resultado = vueloService.buscarPorDestino("Santa Marta");

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(vueloRepository, times(1)).findVuelosByDestino("Santa Marta");
    }
}

