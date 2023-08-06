package com.jpa.relation.exampleEntities.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "profesores")
//@PrimaryKeyJoinColumn(referencedColumnName = "pro_person_id")
public class Professor extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id")
    private Long id;
    @Column(name = "pro_especialidad")
    private String especialidad;
}
