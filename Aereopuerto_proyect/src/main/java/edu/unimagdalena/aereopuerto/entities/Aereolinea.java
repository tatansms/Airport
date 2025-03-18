package edu.unimagdalena.aereopuerto.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Aereolinea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAereolinea;
    private String nombre;

    @ManyToMany
    @JoinTable(name = "aereolineaVuelo",
            joinColumns = @JoinColumn(name = "aereolineaID"),
            inverseJoinColumns = @JoinColumn(name = "vueloID"))
    private Set<Vuelo> vuelos;
}



