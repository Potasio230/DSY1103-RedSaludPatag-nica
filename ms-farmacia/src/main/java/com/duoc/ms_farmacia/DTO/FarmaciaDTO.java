package com.duoc.ms_farmacia.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FarmaciaDTO {


    private Long id;
    private String medicamentos;
    private int stockMedicamentos;
    private String encargadoNombre;
    private String telefonoFarmacia;
    private String proveedor;
    private String telefonoProveedor;
    private String horarioFarmacia;

}
