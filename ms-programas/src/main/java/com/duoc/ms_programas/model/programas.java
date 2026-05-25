package com.duoc.ms_programas.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "programas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class programas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombrePrograma;
    private String nombreEncargado;
    private String tipoPrograma;
    private String lugarPrograma;

    private LocalDate fechaPrograma;
}
