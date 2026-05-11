package com.duoc.ms_farmacia.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "farmacia")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class farmacia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "medicamentos", nullable = false, length = 200)
    private String medicamentos;

    @Column(name = "stockMedicamentos", nullable = false)
    private Integer stockMedicamentos;

    @Column(name = "encargadoNombre", nullable = false, length = 100)
    private String encargadoNombre;

    @Column(name = "telefonoFarmacia", nullable = false, length = 100)
    private String telefonoFarmacia;

    @Column(name = "proveedor", nullable = false, length = 200)
    private String proveedor;

    @Column(name = "telefonoProveedor", nullable = false, length = 100)
    private String telefonoProveedor;

    @Column(name = "horarioFarmacia", nullable = false, length = 50)
    private String horarioFarmacia;
}
