package com.duoc.ms_pacientes.model;

//Nuevo import para el proyecto
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "pacientes") // Mapea la clase a la tabla "pacientes"
@Data
@AllArgsConstructor
@NoArgsConstructor

@Schema(
        name = "Pacientes",
        description = "Representa a los pacientes registrados en RedSaludPatagónica"
)
public class pacientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Id autoincremental
    private Long id;


    @Schema(
            title = "Nombre del paciente",
            description = "Nombre del paciente registrado en RedSaludPatagónica",
            example = "Juan Pérez"
    )
    @Column(name = "nombre", nullable = false, length = 100) //Usamos colum para definir la columna y datos que queremos integrales
    private String nombre;

    @Schema(
            title = "Dirección del paciente",
            description = "Dirección del paciente registrado en RedSaludPatagónica",
            example = "Av. Los Carrera 1234"
    )
    @Column(name = "direccion", nullable = false, length = 200)
    private String direccion;

    @Schema(
            title = "Lugar de residencia del paciente",
            description = "Lugar de residensia del paciente registrado en RedSaludPatagónica",
            example = "Santiago"
    )
    @Column(name = "residencia", nullable = false, length = 200)
    private String residencia;

    @Schema(
            title = "Fecha de nacimiento del paciente",
            description = "Fecha de nacimiento del paciente registrado en RedSaludPatagónica",
            example = "15-08-1998"
    )
    @JsonFormat(pattern = "dd-MM-yyyy") // Controla formato de fecha en JSON
    private LocalDate fechaNacimiento;

    @Schema(
            title = "Email del paciente",
            description = "Dirección del correo electrónico del paciente registrado en RedSaludPatagónica",
            example = "sebastian.rojas@gmail.com"
    )
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Schema(
            title = "Teléfono del paciente",
            description = "Número telefónico del paciente registrado en RedSaludPatagónica",
            example = "987654321"
    )
    @Column(name = "telefono", nullable = false, length = 11)
    private String telefono;

    @Schema(
            title = "Rut del paciente",
            description = "En caso de que paciente registrado en RedSaludPatagónica tenga rut, este podrá registrarlo, de lo contrario, como es opcional no hay problema si no tiene",
            example = "987654321"
    )
    // Dentro del proyecto, el rut no puede ser obligatorio en el formulario, lo ponemos como un valor que puede estar vacio
    @Column(name = "rut", nullable = true, length = 10)
    private String rut;


}
