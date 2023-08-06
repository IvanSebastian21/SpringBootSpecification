package com.jpa.relation.exampleEntities.bnc;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cue_codigo")
    private Long cueCodigo;

    @Column(name = "cue_descripcion")
    private String cueDescripcion;

    @Column(name = "cue_titular")
    private String cueTitular;

    @ManyToOne
    @JoinColumn(name = "cue_banco_codigo")
    private Banco banco;

    @ManyToOne
    @JoinColumn(name = "cue_moneda_codigo")
    private Moneda moneda;

}
