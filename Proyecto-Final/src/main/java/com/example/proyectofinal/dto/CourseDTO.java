package com.example.proyectofinal.dto;

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
public class CourseDTO {

    private Integer idCourse;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50)
    private String nameCourse;

    @NotNull
    @NotEmpty
    @Size(max = 10)
    private String initialsCourse;

    @NotNull
    private boolean enabledCourse;

}
