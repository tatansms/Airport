package edu.unimagdalena.aereopuerto.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pasaporte {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String numero;

    @OneToOne(mappedBy = "pasaporte", cascade = CascadeType.ALL)
    private Pasajero pasajero;
}

