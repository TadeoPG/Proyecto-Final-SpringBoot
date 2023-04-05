package com.example.proyectofinal.service.impl;

import com.example.proyectofinal.model.Enrollment;
import com.example.proyectofinal.model.EnrollmentDetail;
import com.example.proyectofinal.repository.IEnrollmentRepo;
import com.example.proyectofinal.repository.IGenericRepo;
import com.example.proyectofinal.service.IEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl extends CRUDimpl<Enrollment, Integer> implements IEnrollmentService {

    private final IEnrollmentRepo enrollRepo;

    @Override
    protected IGenericRepo<Enrollment, Integer> getRepo() {
        return enrollRepo;
    }

    @Override
    public Map<String, String> studentsGroupedByCourse() throws Exception {

        Stream<List<EnrollmentDetail>> stream = enrollRepo.findAll()
                .stream()
                .map(Enrollment::getDetails);

        Stream<EnrollmentDetail> enrollmentDetailStream = stream.flatMap(Collection::stream);

        Map<String, String> cursoEstudiantes = enrollmentDetailStream
                .collect(Collectors.groupingBy(x -> x.getCourse().getName()
                        , Collectors.mapping(s -> s.getEnrollment().getStudent().getNames()
                                , Collectors.joining(", "))));

        return cursoEstudiantes;
    }

    @Override
    public Map<String, String> groupedByInitials() throws Exception {
        Stream<List<EnrollmentDetail>> stream = enrollRepo.findAll()
                .stream()
                .map(Enrollment::getDetails);

        Stream<EnrollmentDetail> enrollmentDetailStream = stream.flatMap(Collection::stream);

        Map<String, String> mapa = enrollmentDetailStream
                .collect(Collectors.groupingBy(x -> x.getCourse().getInitials()
                        , Collectors.mapping(s -> s.getEnrollment().getStudent().getNames()
                                , Collectors.joining(", "))));

        Map<String, String> mapita = enrollRepo.findAll().stream().map(Enrollment::getDetails)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(x -> x.getCourse().getInitials()
                        , Collectors.mapping(s -> s.getEnrollment().getStudent().getNames()
                                , Collectors.joining(", "))));


        return mapa;
    }


}
