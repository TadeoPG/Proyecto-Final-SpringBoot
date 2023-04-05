package com.example.proyectofinal.service.impl;

import com.example.proyectofinal.model.Course;
import com.example.proyectofinal.repository.ICourseRepo;
import com.example.proyectofinal.repository.IGenericRepo;
import com.example.proyectofinal.service.ICourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl extends CRUDimpl<Course, Integer> implements ICourseService {

    private final ICourseRepo repo;

    @Override
    protected IGenericRepo<Course, Integer> getRepo() {
        return repo;
    }
}
