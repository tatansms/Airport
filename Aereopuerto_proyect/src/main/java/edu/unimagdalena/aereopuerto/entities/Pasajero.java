package edu.unimagdalena.aereopuerto.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString(exclude = "reservas")
@EqualsAndHashCode(exclude = "reservas")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pasajero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String NID;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pasaporteID", nullable = false, referencedColumnName = "id")
    private Pasaporte pasaporte;

    @OneToMany(mappedBy = "pasajero")
    private Set<Reserva> reservas;
}
