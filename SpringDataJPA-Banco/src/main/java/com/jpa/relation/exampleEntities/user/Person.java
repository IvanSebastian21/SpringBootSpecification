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
@Entity(name = "personas")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "per_id")
    private Long id;
    @Column(name = "per_name")
    private String name;
    @Column(name = "per_last_name")
    private String lastName;
    @Column(name = "per_direction")
    private String direction;
    @Column(name = "per_phone")
    private String phone;
}
