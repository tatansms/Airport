package edu.unimagdalena.aereopuerto.repositories;

import edu.unimagdalena.aereopuerto.entities.Aereolinea;
import edu.unimagdalena.aereopuerto.entities.Vuelo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.TestcontainersConfiguration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Testcontainers
@Import(TestcontainersConfiguration.class)
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AereolineaRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("testPostgres")
            .withUsername("testUsername")
            .withPassword("testPassword");

    static {
        postgreSQLContainer.start();
    }

    Set<Vuelo> vuelos = new HashSet<>();
    Aereolinea aereolinea1;
    Aereolinea aereolinea2;

    @Autowired
    AereolineaRepository aereolineaRepository;

    @Autowired
    VueloRepository vueloRepository;

    @BeforeEach
    void setUp() {
        aereolineaRepository.deleteAll();
        vueloRepository.deleteAll();

        Vuelo vuelo_1 = Vuelo.builder().origen("Fundacion").destino("Medellin").build();
        Vuelo vuelo_2 = Vuelo.builder().origen("Cali").destino("Santa Marta").build();
        vuelos.add(vuelo_1);
        vuelos.add(vuelo_2);
        vueloRepository.saveAll(vuelos);

        aereolinea1 = Aereolinea.builder().nombre("Dorado").vuelos(vuelos).build();
        aereolinea2 = Aereolinea.builder().nombre("Wingo Air").vuelos(new HashSet<>()).build();

        aereolinea1 = aereolineaRepository.save(aereolinea1);
        aereolinea2 = aereolineaRepository.save(aereolinea2);

    }

    @AfterEach
    void tearDown() {
        aereolineaRepository.deleteAll();
        vueloRepository.deleteAll();
    }

    @Test
    @Order(1)
    void findAereolineaByNombreStartingWith() {
        List<Aereolinea> aereolineasTest = aereolineaRepository.findAereolineaByNombreStartingWith("D");
        assertFalse(aereolineasTest.isEmpty());
        assertEquals(1, aereolineasTest.size());
        assertEquals("Dorado", aereolineasTest.get(0).getNombre());
    }

    @Test
    @Order(2)
    void findAereolineaByIdAereolinea() {
        Aereolinea aereolineaTest = aereolineaRepository.findAereolineaByIdAereolinea(aereolinea1.getIdAereolinea());
        assertNotNull(aereolineaTest);
        assertEquals("Dorado", aereolineaTest.getNombre());
    }

    @Test
    @Order(3)
    void findAereolineaByNombre() {
        Aereolinea aereolineaTest = aereolineaRepository.findAereolineaByNombre("Wingo Air");
        assertNotNull(aereolineaTest);
        assertEquals(aereolinea2.getIdAereolinea(), aereolineaTest.getIdAereolinea());
    }

    @Test
    @Order(4)
    void findAereolineaByIdAereolineaIn() {
        List<Long> ids = List.of(aereolinea1.getIdAereolinea(), aereolinea2.getIdAereolinea());
        List<Aereolinea> aereolineasTest = aereolineaRepository.findAereolineaByIdAereolineaIn(ids);

        assertFalse(aereolineasTest.isEmpty());
        assertEquals(ids.size(), aereolineasTest.size());
    }

    @Test
    @Order(5)
    void findAereolineasByNombreLike() {
        List<Aereolinea> aereolineasTest = aereolineaRepository.findAereolineasByNombreLike("%Dora%");
        assertFalse(aereolineasTest.isEmpty());
        assertEquals(1, aereolineasTest.size());
        assertEquals("Dorado", aereolineasTest.get(0).getNombre());
    }

    @Test
    @Order(6)
    void getAirlinesWithFlights() {
        List<Aereolinea> aereolineasTest = aereolineaRepository.getAirlinesWithFlights();
        assertFalse(aereolineasTest.isEmpty());
        assertEquals(1, aereolineasTest.size());
        assertEquals("Dorado", aereolineasTest.get(0).getNombre());
    }

    @Test
    @Order(7)
    void getAirlinesByDestination() {
        List<Aereolinea> aereolineasTest = aereolineaRepository.getAirlinesByDestination("Medellin");
        assertFalse(aereolineasTest.isEmpty());
        assertEquals(1, aereolineasTest.size());
        assertEquals("Dorado", aereolineasTest.get(0).getNombre());
    }

    @Test
    @Order(8)
    void getAirlineWithMoreFlights() {
        Aereolinea aereolineaTest = aereolineaRepository.getAirlineWithMoreFlights();
        assertNotNull(aereolineaTest);
        assertEquals("Dorado", aereolineaTest.getNombre());
    }

    @Test
    @Order(9)
    void getAirlinesWithoutFlights() {
        List<Aereolinea> aereolineasTest = aereolineaRepository.getAirlinesWithoutFlights();
        assertFalse(aereolineasTest.isEmpty());
        assertEquals(1, aereolineasTest.size());
        assertEquals("Wingo Air", aereolineasTest.get(0).getNombre());
    }

    @Test
    @Order(10)
    void countAirlines() {
        Long countAirlines = aereolineaRepository.countAirlines();
        assertEquals(2L, countAirlines);
    }
}