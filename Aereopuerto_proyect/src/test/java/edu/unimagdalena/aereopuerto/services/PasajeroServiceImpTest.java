package edu.unimagdalena.aereopuerto.services;

import edu.unimagdalena.aereopuerto.entities.Pasajero;
import edu.unimagdalena.aereopuerto.repositories.PasajeroRepository;
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
class PasajeroServiceImpTest {

    @Mock
    private PasajeroRepository pasajeroRepository;

    @InjectMocks
    private PasajeroServiceImp pasajeroServiceImp;

    private Pasajero pasajero;

    @BeforeEach
    void setUp() {
        pasajero = new Pasajero(1L, "Sebastian Lopez", "25A", null, null);
    }

    @Test
    void obtenerTodosOrdenadosPorNombre() {
        when(pasajeroRepository.findPasajerosAllByOrderByNombreAsc()).thenReturn(Arrays.asList(pasajero));
        List<Pasajero> resultado = pasajeroServiceImp.obtenerTodosOrdenadosPorNombre();
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(pasajeroRepository, times(1)).findPasajerosAllByOrderByNombreAsc();
    }

    @Test
    void obtenerPorId() {
        when(pasajeroRepository.findPasajeroById(1L)).thenReturn(pasajero);
        Optional<Pasajero> resultado = pasajeroServiceImp.obtenerPorId(1L);
        assertTrue(resultado.isPresent());
        assertEquals("Sebastian Lopez", resultado.get().getNombre());
        verify(pasajeroRepository, times(1)).findPasajeroById(1L);
    }

    @Test
    void obtenerPorNombre() {
        when(pasajeroRepository.findPasajeroByNombre("Sebastian Lopez")).thenReturn(pasajero);
        Optional<Pasajero> resultado = pasajeroServiceImp.obtenerPorNombre("Sebastian Lopez");
        assertTrue(resultado.isPresent());
        assertEquals("Sebastian Lopez", resultado.get().getNombre());
        verify(pasajeroRepository, times(1)).findPasajeroByNombre("Sebastian Lopez");
    }

    @Test
    void guardar() {
        when(pasajeroRepository.save(any(Pasajero.class))).thenReturn(pasajero);
        Pasajero resultado = pasajeroServiceImp.guardar(pasajero);
        assertNotNull(resultado);
        assertEquals("Sebastian Lopez", resultado.getNombre());
        verify(pasajeroRepository, times(1)).save(pasajero);
    }

    @Test
    void eliminar() {
        doNothing().when(pasajeroRepository).deleteById(1L);
        pasajeroServiceImp.eliminar(1L);
        verify(pasajeroRepository, times(1)).deleteById(1L);
    }

    @Test
    void contarPasajeros() {
        when(pasajeroRepository.countPassengers()).thenReturn(10L);
        Long resultado = pasajeroServiceImp.contarPasajeros();
        assertEquals(10L, resultado);
        verify(pasajeroRepository, times(1)).countPassengers();
    }

    @Test
    void obtenerUltimos5Pasajeros() {
        when(pasajeroRepository.getLast5Passengers()).thenReturn(Arrays.asList(pasajero));
        List<Pasajero> resultado = pasajeroServiceImp.obtenerUltimos5Pasajeros();
        assertFalse(resultado.isEmpty());
        verify(pasajeroRepository, times(1)).getLast5Passengers();
    }

    @Test
    void obtenerPasajerosConReservas() {
        when(pasajeroRepository.getPassengersWithReservations()).thenReturn(Arrays.asList(pasajero));
        List<Pasajero> resultado = pasajeroServiceImp.obtenerPasajerosConReservas();
        assertFalse(resultado.isEmpty());
        verify(pasajeroRepository, times(1)).getPassengersWithReservations();
    }

    @Test
    void obtenerPasajerosSinReservas() {
        when(pasajeroRepository.findPassengersWithoutReservations()).thenReturn(Arrays.asList(pasajero));
        List<Pasajero> resultado = pasajeroServiceImp.obtenerPasajerosSinReservas();
        assertFalse(resultado.isEmpty());
        verify(pasajeroRepository, times(1)).findPassengersWithoutReservations();
    }

    @Test
    void obtenerPasajeroConMasReservas() {
        when(pasajeroRepository.getPassengerWithMoreBookings()).thenReturn(pasajero);
        Optional<Pasajero> resultado = pasajeroServiceImp.obtenerPasajeroConMasReservas();
        assertTrue(resultado.isPresent());
        assertEquals("Sebastian Lopez", resultado.get().getNombre());
        verify(pasajeroRepository, times(1)).getPassengerWithMoreBookings();
    }
}
