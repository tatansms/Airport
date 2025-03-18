package edu.unimagdalena.aereopuerto.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vuelo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, updatable = false) @Builder.Default
    private UUID numeroVuelo = UUID.randomUUID();

    @Column(nullable = false)
    private String origen;

    @Column(nullable = false)
    private String destino;

    @ManyToMany(mappedBy = "vuelos")
    private Set<Aereolinea> aereolineas;

    @OneToMany(mappedBy = "vuelo")
    private Set<Reserva> reservas;
}
