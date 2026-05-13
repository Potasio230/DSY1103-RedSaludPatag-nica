package com.duoc.ms_recetas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecetasDTO {

    @NotNull
    @Positive
    private Long id;

    @NotNull
    private LocalDateTime fechaEmision;

    @NotBlank(message = "La ficha del paciente es obligatoria")
    @Positive(message = "La ficha del paciente debe ser positiva")
    private Integer fichaPaciente;

    @NotBlank(message = "El nombre del profesional es obligatorio")
    @Size(min = 2, max = 50)
    private String nombreProfesional;

    @NotNull(message = "La ficha del profesional es obligatoria")
    private String fichaProfesional;

    @NotNull(message = "La razon es obligatoria")
    private String razonConsulta;

    @NotNull(message = "La modalidad es obligatoria")
    private String modalidad;

    @NotNull
    private LocalDateTime fechaConsulta;
}
