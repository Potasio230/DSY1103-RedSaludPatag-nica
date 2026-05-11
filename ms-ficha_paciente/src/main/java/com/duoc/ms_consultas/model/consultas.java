package com.duoc.ms_consultas.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "consultas")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class consultas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombrePaciente", nullable = false, length = 100)
    private String nombrePaciente;

    @Column(name = "fichaPaciente", nullable = false)
    private Integer fichaPaciente;

    @Column(name = "nombreProfesional", nullable = false, length = 100)
    private String nombreProfesional;

    @Column(name = "fichaProfesional", nullable = false)
    private Integer fichaProfesional;

    @Column(name = "razonConsulta", nullable = false, length = 100)
    private String razonConsulta;

    @Column(name = "modalidad", nullable = false, length = 50)
    private String modalidad;

    @Column(name = "fechaConsulta", nullable = false, length = 100)
    private LocalDateTime fechaConsulta;

}
