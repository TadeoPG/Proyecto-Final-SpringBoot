package com.example.proyectofinal.controller;

import com.example.proyectofinal.dto.StudentDTO;
import com.example.proyectofinal.model.Student;
import com.example.proyectofinal.service.IStudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final IStudentService service;

    @Qualifier("studentMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> readAll() throws Exception {
        List<StudentDTO> list = service.readAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Student obj = service.readById(id);
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> create(@Valid @RequestBody StudentDTO dto) throws Exception {
        Student obj = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody StudentDTO dto) throws Exception {
        dto.setIdStudent(id);
        Student obj = service.update(convertToEntity(dto), id);
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getStudentsOrdered")
    public ResponseEntity<List<StudentDTO>> getStudentsOrderedByAgeDesc() {
        List<StudentDTO> list = service.getStudentsOrderedByAgeDesc().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    private StudentDTO convertToDto(Student st) {
        return mapper.map(st, StudentDTO.class);
    }

    private Student convertToEntity(StudentDTO dto) {
        return mapper.map(dto, Student.class);
    }

}
