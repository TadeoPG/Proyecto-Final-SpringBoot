package com.example.proyectofinal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Student {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStudent;

    @Column(nullable = false, length = 50)
    private String names;

    @Column(nullable = false, length = 50)
    private String lastNames;

    @Column(nullable = false, length = 10)
    private String idCard;

    @EqualsAndHashCode.Include
    @Column(nullable = false)
    private Integer age;
}
