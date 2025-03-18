package edu.unimagdalena.aereopuerto.repositories;

import edu.unimagdalena.aereopuerto.entities.Pasajero;
import edu.unimagdalena.aereopuerto.entities.Pasaporte;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.TestcontainersConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Testcontainers
@Import(TestcontainersConfiguration.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PasaporteRepositoryTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("testPostgres")
            .withUsername("testUsername")
            .withPassword("testPassword");

    static {
        postgreSQLContainer.start();
    }

    Pasaporte pasaporte1;
    Pasaporte pasaporte2;
    Pasaporte pasaporte3;
    Pasajero pasajero1;
    Pasajero pasajero2;
    Pasajero pasajero3;

    @Autowired
    PasajeroRepository pasajeroRepository;
    @Autowired
    PasaporteRepository pasaporteRepository;

    @BeforeEach
    void setUp() {
        pasajero1 = Pasajero.builder().NID("25A").nombre("Sebastian Lopez").build();
        pasajero2 = Pasajero.builder().NID("21B").nombre("Luis Florez").build();
        pasajero3 = Pasajero.builder().NID("25C").nombre("Juan de la Hoz").build();
        pasaporte1 = Pasaporte.builder().numero("489478AS").pasajero(pasajero1).build();
        pasaporte2 = Pasaporte.builder().numero("A678B").pasajero(pasajero2).build();
        pasaporte3 = Pasaporte.builder().numero("A688C").pasajero(pasajero3).build();

        pasajero1.setPasaporte(pasaporte1);
        pasajero2.setPasaporte(pasaporte2);
        pasajero3.setPasaporte(pasaporte3);

        pasajero1 = pasajeroRepository.save(pasajero1);
        pasajero2 = pasajeroRepository.save(pasajero2);
        pasajero3 = pasajeroRepository.save(pasajero3);
    }

    @AfterEach
    void tearDown() {
        pasaporteRepository.deleteAll();
        pasajeroRepository.deleteAll();
    }

    @Test
    @Order(1)
    void findPasaporteByNumero() {
        Pasaporte pasaporteTest = pasaporteRepository.findPasaporteByNumero("A678B");
        Assertions.assertNotNull(pasaporteTest);
        Assertions.assertEquals("A678B", pasaporteTest.getNumero());
    }

    @Test
    @Order(2)
    void findPasaporteById() {
        Pasaporte pasaporteTest = pasaporteRepository.findPasaporteById(pasajero3.getId());
        Assertions.assertNotNull(pasaporteTest);
        Assertions.assertEquals(pasaporte3.getId(), pasaporteTest.getId());
    }

    @Test
    @Order(3)
    void findPasaportesByNumeroContaining() {
        List<Pasaporte> pasaporteTest = pasaporteRepository.findPasaportesByNumeroContaining("A6");
        Assertions.assertNotNull(pasaporteTest);
        Assertions.assertEquals(2, pasaporteTest.size());
    }

    @Test
    @Order(4)
    void findPasaportesByIdIn() {
        List<Long> idsTest = List.of(pasaporte1.getId(), pasaporte3.getId());
        List<Pasaporte> pasaportesTest = pasaporteRepository.findPasaportesByIdIn(idsTest);
        Assertions.assertFalse(pasaportesTest.isEmpty());
        Assertions.assertEquals(idsTest.size(), pasaporteRepository.findPasaportesByIdIn(idsTest).size());
    }

    @Test
    @Order(5)
    void findPasaportesByNumeroStartingWith() {
        List<Pasaporte> pasaportesTest = pasaporteRepository.findPasaportesByNumeroStartingWith("A6");
        Assertions.assertFalse(pasaportesTest.isEmpty());
        Assertions.assertEquals(2, pasaportesTest.size());
    }

    @Test
    @Order(6)
    void findPassportWithLongestNumber() {
        Pasaporte pasaporteTest = pasaporteRepository.findPassportWithLongestNumber();
        Assertions.assertNotNull(pasaporteTest);
        Assertions.assertEquals(pasajero1.getId(), pasaporteTest.getId());
    }

    @Test
    @Order(7)
    void getAllOrderedById() {
        List<Pasaporte> pasaporteTest = pasaporteRepository.findAll();
        Assertions.assertFalse(pasaporteTest.isEmpty());
        Assertions.assertEquals(3, pasaporteTest.size());
    }

    @Test
    @Order(8)
    void getPassportsInIdRange() {
        List<Pasaporte> pasaportesTest = pasaporteRepository.getPassportsInIdRange(pasaporte2.getId(), pasaporte3.getId());
        Assertions.assertFalse(pasaportesTest.isEmpty());
        Assertions.assertEquals(2, pasaportesTest.size());
    }

    @Test
    @Order(9)
    void getLastRegisteredPassport() {
        Pasaporte pasaporteTest = pasaporteRepository.getLastRegisteredPassport();
        Assertions.assertNotNull(pasaporteTest);
        Assertions.assertEquals(pasaporte3.getNumero(), pasaporteTest.getNumero());
    }

    @Test
    @Order(10)
    void countPassports() {
        Long count = pasaporteRepository.countPassports();
        Assertions.assertEquals(3, count);
    }
}