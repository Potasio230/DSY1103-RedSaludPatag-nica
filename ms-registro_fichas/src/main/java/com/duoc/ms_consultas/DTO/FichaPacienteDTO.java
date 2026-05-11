package com.duoc.ms_consultas.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class FichaPacienteDTO {
    private Long id;
    private String nombrePaciente;
    private String fichaPaciente;
    private String nombreProfesional;
    private String fichaProfesional;
    private String razonConsulta;
    private String modalidad;
    private LocalDateTime fechaConsulta;


}
