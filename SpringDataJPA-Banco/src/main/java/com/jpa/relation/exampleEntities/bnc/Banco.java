package com.jpa.relation.exampleEntities.bnc;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "bancos")
public class Banco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ban_codigo")
    private Long banCodigo;

    @Column(name = "ban_nombre")
    private String banNombre;
}
