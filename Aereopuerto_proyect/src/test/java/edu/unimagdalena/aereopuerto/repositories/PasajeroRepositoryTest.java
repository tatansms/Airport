package edu.unimagdalena.aereopuerto.repositories;

import edu.unimagdalena.aereopuerto.entities.Pasajero;
import edu.unimagdalena.aereopuerto.entities.Pasaporte;
import edu.unimagdalena.aereopuerto.entities.Reserva;
import edu.unimagdalena.aereopuerto.entities.Vuelo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.TestcontainersConfiguration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Testcontainers
@Import(TestcontainersConfiguration.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PasajeroRepositoryTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("testPostgres")
            .withUsername("testUsername")
            .withPassword("testPassword");

    static {
        postgreSQLContainer.start();
    }

    Pasajero pasajero1;
    Pasajero pasajero2;
    Pasajero pasajero3;
    Set<Reserva> reservas1 = new HashSet<>();
    Set<Reserva> reservas2 = new HashSet<>();
    Set<Reserva> reservas3 = new HashSet<>();
    Vuelo vuelo1;
    Vuelo vuelo2;
    Vuelo vuelo3;
    Vuelo vuelo4;
    Vuelo vuelo5;
    Pasaporte pasaporte1;
    Pasaporte pasaporte2;
    Pasaporte pasaporte3;

    @Autowired
    PasajeroRepository pasajeroRepository;
    @Autowired
    ReservaRepository reservaRepository;
    @Autowired
    VueloRepository vueloRepository;
    @Autowired
    PasaporteRepository pasaporteRepository;

    @BeforeEach
    void setUp() {
        pasaporte1 = pasaporteRepository.save(Pasaporte.builder().numero("B894A").build());
        pasaporte2 = pasaporteRepository.save(Pasaporte.builder().numero("A578B").build());
        pasaporte3 = pasaporteRepository.save(Pasaporte.builder().numero("A588C").build());

        pasajero1 = pasajeroRepository.save(Pasajero.builder().NID("25A").nombre("Sebastian Lopez").pasaporte(pasaporte1).reservas(reservas1).build());
        pasajero2 = pasajeroRepository.save(Pasajero.builder().NID("21B").nombre("Luis Florez").pasaporte(pasaporte2).reservas(reservas2).build());
        pasajero3 = pasajeroRepository.save(Pasajero.builder().NID("25C").nombre("Juan de la Hoz").pasaporte(pasaporte3).reservas(reservas3).build());

        vueloRepository.save(vuelo1 = Vuelo.builder().origen("Medellin").destino("Bogotá").build());
        vueloRepository.save(vuelo2 = Vuelo.builder().origen("Medellin").destino("Tunja").build());
        vueloRepository.save(vuelo3 = Vuelo.builder().origen("Santa Marta").destino("Cali").build());
        vueloRepository.save(vuelo4 = Vuelo.builder().origen("Chivolo").destino("Barranquilla").build());
        vueloRepository.save(vuelo5 = Vuelo.builder().origen("Suiza").destino("Madrid").build());

        Reserva reserva1 = Reserva.builder().pasajero(pasajero1).vuelo(vuelo1).build();
        Reserva reserva2 = Reserva.builder().pasajero(pasajero1).vuelo(vuelo2).build();
        Reserva reserva3 = Reserva.builder().pasajero(pasajero3).vuelo(vuelo3).build();

        reservas1.add(reserva1);
        reservas1.add(reserva2);
        reservas1.add(reserva3);
        reservas2.add(reserva3);
        reservas2.add(reserva1);
        reservaRepository.saveAll(reservas1);
        reservaRepository.saveAll(reservas2);
    }

    @AfterEach
    void tearDown() {
        reservaRepository.deleteAll();
        pasajeroRepository.deleteAll();
        pasaporteRepository.deleteAll();
        vueloRepository.deleteAll();
    }

    @Test
    @Order(1)
    void findPasajerosAllByOrderByNombreAsc() {
        List<Pasajero> pasajerosTest = pasajeroRepository.findPasajerosAllByOrderByNombreAsc();
        Assertions.assertFalse(pasajerosTest.isEmpty());
        Assertions.assertEquals(3, pasajerosTest.size());
    }

    @Test
    @Order(2)
    void findPasajeroById() {
        Pasajero pasajeroTest = pasajeroRepository.findPasajeroById(pasajero2.getId());
        Assertions.assertNotNull(pasajeroTest);
        Assertions.assertEquals(pasajero2.getId(), pasajeroTest.getId());
    }

    @Test
    @Order(3)
    void findPasajeroByNombre() {
        Pasajero pasajeroTest = pasajeroRepository.findPasajeroByNombre(pasajero3.getNombre());
        Assertions.assertNotNull(pasajeroTest);
        Assertions.assertEquals(pasajero3.getNombre(), pasajeroTest.getNombre());
    }

    @Test
    @Order(4)
    void findPasajerosByIdIn() {
        List<Long> idsTest = List.of(pasajero2.getId(), pasajero3.getId());
        List<Pasajero> pasajerosTest = pasajeroRepository.findPasajerosByIdIn(idsTest);

        Assertions.assertFalse(pasajerosTest.isEmpty());
        Assertions.assertEquals(idsTest.size(), pasajerosTest.size());
    }

    @Test
    @Order(5)
    void findPasajerosByNombreLike() {
        List<Pasajero> pasajerosTest = pasajeroRepository.findPasajerosByNombreLike("%Jua%");
        Assertions.assertFalse(pasajerosTest.isEmpty());
        Assertions.assertEquals(1, pasajerosTest.size());
    }

    @Test
    @Order(6)
    void getPassengerWithMoreBookings() {
        Pasajero pasajeroTest = pasajeroRepository.getPassengerWithMoreBookings();
        Assertions.assertNotNull(pasajeroTest);
        Assertions.assertEquals(pasajero1.getReservas().size(), pasajeroTest.getReservas().size());
    }

    @Test
    @Order(7)
    void countPassengers() {
        Long totalPassengers = pasajeroRepository.countPassengers();
        Assertions.assertEquals(3, totalPassengers);
    }

    @Test
    @Order(8)
    void getLast5Passengers() {
        List<Pasajero> pasajerosTest = pasajeroRepository.getLast5Passengers();
        Assertions.assertFalse(pasajerosTest.isEmpty());
        Assertions.assertEquals(3, pasajerosTest.size());
    }

    @Test
    @Order(9)
    void getPassengersWithReservations() {
        List<Pasajero> pasajerosTest = pasajeroRepository.getPassengersWithReservations();
        Assertions.assertFalse(pasajerosTest.isEmpty());
        Assertions.assertEquals(2, pasajerosTest.size());
    }

    @Test
    @Order(10)
    void findPassengersWithoutReservations() {
        List<Pasajero> pasejerosTest = pasajeroRepository.findPassengersWithoutReservations();
        Assertions.assertFalse(pasejerosTest.isEmpty());
        Assertions.assertEquals(1, pasejerosTest.size());
    }
}