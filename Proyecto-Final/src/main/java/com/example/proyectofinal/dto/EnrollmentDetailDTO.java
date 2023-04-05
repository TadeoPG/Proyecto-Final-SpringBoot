package com.example.proyectofinal.dto;

import com.example.proyectofinal.model.Course;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnrollmentDetailDTO {

    @JsonBackReference
    private EnrollmentDTO enrollment;

    @NotNull
    private CourseDTO course;

    @NotNull
    @NotEmpty
    @Size(max = 20)
    private String classroom;
}
