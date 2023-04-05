package com.example.proyectofinal.service;

import com.example.proyectofinal.model.Student;

import java.util.List;

public interface IStudentService extends ICRUD<Student, Integer> {

    List<Student> getStudentsOrderedByAgeDesc();

}
