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
@Entity(name = "estudiantes")
//@PrimaryKeyJoinColumn(referencedColumnName = "stu_person_id")
public class Student extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stu_id")
    private Long id;
    @Column(name = "stu_note")
    private String note;
}
