package com.duoc.redsalud.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
// SE IMPORTA EL JSONFORMAT PARA ESTANDARIZAR LA FECHA COMO ES CORRECTA Y SIMPLEMENTE APAREZCAN LOS DATOS QUE IMPORTA, LOS CUALES SON "DD-MM-YYYY"
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table (name = "pacientes")
@Data
public class redsalud {
    @Id // GENERAMOS EL ID COMO UNA ENTIDAD TENIENDO COMO TIPO DE DATO LONG
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

    // CONSTRUCTOR VACIO
    public redsalud() {
    }

    // CONSTRUCTOR PRINCIPAL
    public redsalud(Long id, String nombre, String direccion, String residencia, LocalDate fechaNacimiento, String email, String telefono, String rut) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.residencia = residencia;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.telefono = telefono;
        this.rut = rut;
    }
    // GETTERS Y SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getResidencia() {
        return residencia;
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }
}
