package com.example.proyectofinal.service.impl;

import com.example.proyectofinal.model.Student;
import com.example.proyectofinal.repository.IGenericRepo;
import com.example.proyectofinal.repository.IStudentRepo;
import com.example.proyectofinal.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl extends CRUDimpl<Student, Integer> implements IStudentService {

    private final IStudentRepo repo;

    @Override
    public List<Student> getStudentsOrderedByAgeDesc() {
        List<Student> list = repo.findAll();
        list.sort((Student st1, Student st2) -> st2.getAge() - st1.getAge());
        return list;
    }

    @Override
    protected IGenericRepo<Student, Integer> getRepo() {
        return repo;
    }
}
