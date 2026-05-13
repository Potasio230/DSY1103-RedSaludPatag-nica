package com.duoc.ms_farmacia.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FarmaciaDTO {

    @NotNull @Positive
    private Long id;

    @NotBlank(message = "El nombre del medicamento es obligatorio")
    private String medicamentos;

    @PositiveOrZero(message = "El stock no puede ser negativo")
    private int stockMedicamentos;

    @NotBlank(message = "El nombre del encargado es obligatorio")
    @Size(min = 2, max = 50)
    private String encargadoNombre;

    @NotBlank(message = "El télefono de la farmacia es obligatorio")
    private String telefonoFarmacia;

    @NotBlank(message = "El proveedor es obligatorio")
    private String proveedor;

    @NotBlank(message = "El teléfono del proveedor es obligatorio")
    private String telefonoProveedor;

    @NotBlank(message = "El horario es obligatorio")
    private String horarioFarmacia;

}
