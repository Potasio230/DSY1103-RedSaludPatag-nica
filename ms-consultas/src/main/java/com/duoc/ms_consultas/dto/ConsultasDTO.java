package com.duoc.ms_consultas.dto;

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
public class ConsultasDTO {

    @NotNull
    @Positive
    private Long id;

    @NotBlank(message =  "El nombre del paciente es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre del paciente debe tener entre 2 y 50 caracteres")
    private String nombrePaciente;

    @NotBlank(message = "La ficha del paciente es obligatoria")
    @Positive(message = "La ficha del paciente debe ser positiva")
    private Integer fichaPaciente;

    @NotBlank(message = "El nombre del profesional es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre del profesional debe tener entre 2 y 50 caracteres")
    private String nombreProfesional;

    @NotBlank(message = "La ficha del profesional es obligatorio")
    @Positive(message = "La ficha del profesional debe ser positiva")
    private Integer fichaProfesional;

    @NotBlank(message = "La razón de la consulta es obligatoria")
    private String razonConsulta;

    @NotBlank(message = "La modalidad es obligatoria")
    private String modalidad;

    @NotNull
    private LocalDateTime fechaConsulta;


}
