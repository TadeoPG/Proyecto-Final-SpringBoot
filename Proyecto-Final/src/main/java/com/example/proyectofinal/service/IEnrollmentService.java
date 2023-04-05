package com.example.proyectofinal.service;

import com.example.proyectofinal.model.Enrollment;

import java.util.Map;

public interface IEnrollmentService extends ICRUD<Enrollment, Integer> {

    Map<String, String> studentsGroupedByCourse() throws Exception;

    Map<String, String> groupedByInitials() throws Exception;
}
