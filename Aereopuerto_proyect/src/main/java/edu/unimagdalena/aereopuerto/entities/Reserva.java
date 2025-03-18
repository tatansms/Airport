package edu.unimagdalena.aereopuerto.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString(exclude = {"pasajero", "vuelo"})
@EqualsAndHashCode(exclude = {"pasajero", "vuelo"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, updatable = false) @Builder.Default
    private UUID codigoReserva = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "pasajeroID", nullable = false)
    private Pasajero pasajero;

    @ManyToOne
    @JoinColumn(name = "vueloID", nullable = false)
    private Vuelo vuelo;
}
