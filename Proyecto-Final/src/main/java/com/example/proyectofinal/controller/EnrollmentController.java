package com.example.proyectofinal.controller;

import com.example.proyectofinal.dto.EnrollmentDTO;
import com.example.proyectofinal.model.Enrollment;
import com.example.proyectofinal.repository.IEnrollmentRepo;
import com.example.proyectofinal.service.IEnrollmentService;
import com.example.proyectofinal.service.impl.EnrollmentServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final IEnrollmentService service;

    @Qualifier("enrollmentMapper")
    private final ModelMapper mapper;

    @GetMapping("/listar")
    public ResponseEntity<List<EnrollmentDTO>> readAll() throws Exception {
        List<EnrollmentDTO> list = service.readAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Enrollment obj = service.readById(id);
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EnrollmentDTO> create(@Valid @RequestBody EnrollmentDTO dto) throws Exception {
        Enrollment obj = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody EnrollmentDTO dto) throws Exception {
        dto.setIdEnrollment(id);
        Enrollment obj = service.update(convertToEntity(dto), id);
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getAgrupados")
    public ResponseEntity<Map<String, String>> studentsGroupedByCourse() throws Exception {
        Map<String, String> mapa = service.studentsGroupedByCourse();
        return new ResponseEntity<>(mapa, HttpStatus.OK);
    }

    @GetMapping("/get/StudentsByInitialsCourse")
    public ResponseEntity<Map<String, String>> groupedByInitials() throws Exception {
        Map<String, String> mapa = service.groupedByInitials();
        return new ResponseEntity<>(mapa, HttpStatus.OK);
    }


    private EnrollmentDTO convertToDto(Enrollment st) {
        return mapper.map(st, EnrollmentDTO.class);
    }

    private Enrollment convertToEntity(EnrollmentDTO dto) {
        return mapper.map(dto, Enrollment.class);
    }

}
