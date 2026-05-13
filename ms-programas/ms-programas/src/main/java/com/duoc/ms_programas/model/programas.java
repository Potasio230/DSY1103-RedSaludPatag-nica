package com.duoc.ms_programas.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table (name = "programas")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class programas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombrePrograma", nullable = false, length = 100)
    private String nombrePrograma;

    @Column(name = "nombreEncargado", nullable = false, length = 100)
    private String nombreEncargado;

    @Column(name = "tipoPrograma", nullable = false, length = 100)
    private String tipoPrograma;

    @Column(name = "lugarPrograma", nullable = false, length = 100)
    private String lugarPrograma;

    @JsonFormat(pattern = "dd-mm-yyyy")
    private LocalDate fechaPrograma;
}
