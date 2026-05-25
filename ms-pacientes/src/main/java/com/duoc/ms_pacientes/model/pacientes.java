package com.duoc.ms_pacientes.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "pacientes") // Mapea la clase a la tabla "pacientes"
@Data
@AllArgsConstructor
@NoArgsConstructor

public class pacientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Id autoincremental
    private Long id;


    // Usamos colum para definir la columna y datos que queremos integrales
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "direccion", nullable = false, length = 200)
    private String direccion;

    @Column(name = "residencia", nullable = false, length = 200)
    private String residencia;

    @JsonFormat(pattern = "dd-MM-yyyy") // Controla formato de fecha en JSON
    private LocalDate fechaNacimiento;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "telefono", nullable = false, length = 11)
    private String telefono;

    // Dentro del proyecto, el rut no puede ser obligatorio en el formulario, lo ponemos como un valor que puede estar vacio
    @Column(name = "rut", nullable = true, length = 10)
    private String rut;


}
