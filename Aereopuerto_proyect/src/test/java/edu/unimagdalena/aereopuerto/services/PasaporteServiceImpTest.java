package edu.unimagdalena.aereopuerto.services;


import edu.unimagdalena.aereopuerto.entities.Pasaporte;
import edu.unimagdalena.aereopuerto.repositories.PasaporteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PasaporteServiceImpTest {

    @Mock
    private PasaporteRepository pasaporteRepository;

    @InjectMocks
    private PasaporteServiceImp pasaporteServiceImp;

    private Pasaporte pasaporte;

    @BeforeEach
    void setUp() {
        pasaporte = new Pasaporte(1L, "123456789", null);
    }

    @Test
    void obtenerTodos() {
        when(pasaporteRepository.findAll()).thenReturn(Arrays.asList(pasaporte));

        List<Pasaporte> resultado = pasaporteServiceImp.obtenerTodos();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(pasaporteRepository, times(1)).findAll();
    }

    @Test
    void obtenerPorId() {
        when(pasaporteRepository.findById(1L)).thenReturn(Optional.of(pasaporte));

        Optional<Pasaporte> resultado = pasaporteServiceImp.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("123456789", resultado.get().getNumero());
        verify(pasaporteRepository, times(1)).findById(1L);
    }

    @Test
    void guardar() {
        when(pasaporteRepository.save(any(Pasaporte.class))).thenReturn(pasaporte);

        Pasaporte resultado = pasaporteServiceImp.guardar(pasaporte);

        assertNotNull(resultado);
        assertEquals("123456789", resultado.getNumero());
        verify(pasaporteRepository, times(1)).save(pasaporte);
    }

    @Test
    void eliminar() {
        doNothing().when(pasaporteRepository).deleteById(1L);

        pasaporteServiceImp.eliminar(1L);

        verify(pasaporteRepository, times(1)).deleteById(1L);
    }
}

