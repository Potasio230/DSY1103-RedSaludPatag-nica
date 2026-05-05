package com.duoc.ms_recetas.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "recetas")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class recetas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fechaEmision", nullable = false)
    private LocalDate fechaEmision;

    @Column(name = "idPaciente", nullable = false, length = 100)
    private Integer idPaciente;

    @Column(name = "nombrePaciente", nullable = false)
    private String nombrePaciente;

    @Column(name = "idProfesional", nullable = false)
    private Integer idProfesional;

    @Column(name = "nombreProfesional", nullable = false, length = 100)
    private String nombreProfesional;

    @Column(name = "nombreMedicamentos", nullable = false, length = 100)
    private String nombreMedicamentos;

    @Column(name = "indicacionesMedicas", nullable = false, length = 100)
    private String indicacionesMedicas;

}
