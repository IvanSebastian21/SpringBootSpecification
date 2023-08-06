package com.jpa.relation.exampleEntities.bnc;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "monedas")
public class Moneda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mon_codigo")
    private Long monCodigo;

    @Column(name = "mon_nombre")
    private String monNombre;

    @OneToMany(mappedBy = "moneda")
    private List<Cuenta> cuentaList;
}
