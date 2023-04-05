package com.example.proyectofinal.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {

    private Integer idStudent;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50)
    private String namesStudent;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50)
    private String lastNamesStudent;

    @NotNull
    @NotEmpty
    @Size(min = 8, max = 10)
    private String idCardStudent;

    @NotNull
    private Integer ageStudent;
}
