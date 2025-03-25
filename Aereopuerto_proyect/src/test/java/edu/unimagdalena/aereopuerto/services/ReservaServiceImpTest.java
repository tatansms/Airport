package edu.unimagdalena.aereopuerto.services;

import edu.unimagdalena.aereopuerto.entities.Reserva;
import edu.unimagdalena.aereopuerto.repositories.ReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservaServiceImpTest {

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaServiceImp reservaServiceImp;

    private Reserva reserva;
    private UUID codigoReserva;

    @BeforeEach
    void setUp() {
        codigoReserva = UUID.fromString("3be0e7b7-7c36-4f3d-b822-c3aba5bdf68a");
        reserva = new Reserva(1L, codigoReserva, null, null);
    }

    @Test
    void obtenerTodas() {
        when(reservaRepository.findAll()).thenReturn(Arrays.asList(reserva));
        List<Reserva> resultado = reservaServiceImp.obtenerTodas();
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(reservaRepository, times(1)).findAll();
    }

    @Test
    void obtenerPorId() {
        when(reservaRepository.findReservaById(1L)).thenReturn(reserva);
        Optional<Reserva> resultado = reservaServiceImp.obtenerPorId(1L);
        assertTrue(resultado.isPresent());
        verify(reservaRepository, times(1)).findReservaById(1L);
    }

    @Test
    void obtenerPorCodigoReserva() {
        when(reservaRepository.findByCodigoReserva(codigoReserva)).thenReturn(reserva);
        Optional<Reserva> resultado = reservaServiceImp.obtenerPorCodigoReserva(codigoReserva);
        assertTrue(resultado.isPresent());
        verify(reservaRepository, times(1)).findByCodigoReserva(codigoReserva);
    }

    @Test
    void guardar() {
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);
        Reserva resultado = reservaServiceImp.guardar(reserva);
        assertNotNull(resultado);
        verify(reservaRepository, times(1)).save(reserva);
    }

    @Test
    void eliminar() {
        doNothing().when(reservaRepository).deleteById(1L);
        reservaServiceImp.eliminar(1L);
        verify(reservaRepository, times(1)).deleteById(1L);
    }

    @Test
    void contarReservasPorPasajero() {
        when(reservaRepository.countReservasByPasajeroNombre("Sebastian Lopez")).thenReturn(2L);
        Long resultado = reservaServiceImp.contarReservasPorPasajero("Sebastian Lopez");
        assertEquals(2L, resultado);
        verify(reservaRepository, times(1)).countReservasByPasajeroNombre("Sebastian Lopez");
    }

    @Test
    void obtenerReservasPorPasajero() {
        when(reservaRepository.findReservasByPasajeroId(1L)).thenReturn(Arrays.asList(reserva));
        List<Reserva> resultado = reservaServiceImp.obtenerReservasPorPasajero(1L);
        assertFalse(resultado.isEmpty());
        verify(reservaRepository, times(1)).findReservasByPasajeroId(1L);
    }

    @Test
    void obtenerReservasPorVuelo() {
        when(reservaRepository.findReservasByVueloId(1L)).thenReturn(Arrays.asList(reserva));
        List<Reserva> resultado = reservaServiceImp.obtenerReservasPorVuelo(1L);
        assertFalse(resultado.isEmpty());
        verify(reservaRepository, times(1)).findReservasByVueloId(1L);
    }

    @Test
    void obtenerReservasPorOrigenYDestino() {
        when(reservaRepository.findReservasByOrigenAndDestino("Medellin", "Bogotá")).thenReturn(Arrays.asList(reserva));
        List<Reserva> resultado = reservaServiceImp.obtenerReservasPorOrigenYDestino("Medellin", "Bogotá");
        assertFalse(resultado.isEmpty());
        verify(reservaRepository, times(1)).findReservasByOrigenAndDestino("Medellin", "Bogotá");
    }

    @Test
    void obtenerReservasPorCiudadOrigen() {
        when(reservaRepository.findReservasByCiudadOrigen("Medellin")).thenReturn(Arrays.asList(reserva));
        List<Reserva> resultado = reservaServiceImp.obtenerReservasPorCiudadOrigen("Medellin");
        assertFalse(resultado.isEmpty());
        verify(reservaRepository, times(1)).findReservasByCiudadOrigen("Medellin");
    }

    @Test
    void obtenerReservasPorDestinoVuelo() {
        when(reservaRepository.findReservationsByFlightDestination("Bogotá")).thenReturn(Arrays.asList(reserva));
        List<Reserva> resultado = reservaServiceImp.obtenerReservasPorDestinoVuelo("Bogotá");
        assertFalse(resultado.isEmpty());
        verify(reservaRepository, times(1)).findReservationsByFlightDestination("Bogotá");
    }

    @Test
    void obtenerReservasRecientes() {
        when(reservaRepository.findRecentReservations()).thenReturn(Arrays.asList(reserva));
        List<Reserva> resultado = reservaServiceImp.obtenerReservasRecientes();
        assertFalse(resultado.isEmpty());
        verify(reservaRepository, times(1)).findRecentReservations();
    }
}
