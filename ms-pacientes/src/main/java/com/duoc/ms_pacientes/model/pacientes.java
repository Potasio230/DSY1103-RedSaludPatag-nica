package com.duoc.ms_pacientes.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "pacientes")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class pacientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // USAMOS COLUMN PARA DEFINIR LA COLUMNA Y DATOS QUE QUEREMOS INTEGRARLES
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "direccion", nullable = false, length = 200)
    private String direccion;

    @Column(name = "residencia", nullable = false, length = 200)
    private String residencia;

    @JsonFormat(pattern = "dd-mm-yyyy")
    private LocalDate fechaNacimiento;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "telefono", nullable = false, length = 11)
    private String telefono;

    // DENTRO DEL PROYECTO, AL RUT NO SER OBLIGATORIO EN EL FORMULARIO, LO PONEMOS COMO UN VALOR QUE PUEDE SER VACIO
    @Column(name = "rut", nullable = true, length = 10)
    private String rut;


}
