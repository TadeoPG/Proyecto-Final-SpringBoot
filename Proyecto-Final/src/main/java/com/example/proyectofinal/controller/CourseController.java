package com.example.proyectofinal.controller;

import com.example.proyectofinal.dto.CourseDTO;
import com.example.proyectofinal.model.Course;
import com.example.proyectofinal.service.ICourseService;
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
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final ICourseService service;

    @Qualifier("courseMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> readAll() throws Exception {
        List<CourseDTO> list = service.readAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Course obj = service.readById(id);
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> create(@Valid @RequestBody CourseDTO dto) throws Exception {
        Course obj = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody CourseDTO dto) throws Exception {
        dto.setIdCourse(id);
        Course obj = service.update(convertToEntity(dto), id);
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    private CourseDTO convertToDto(Course st) {
        return mapper.map(st, CourseDTO.class);
    }

    private Course convertToEntity(CourseDTO dto) {
        return mapper.map(dto, Course.class);
    }

}
