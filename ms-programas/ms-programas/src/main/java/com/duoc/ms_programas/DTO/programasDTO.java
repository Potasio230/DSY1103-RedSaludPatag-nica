package com.duoc.ms_programas.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class programasDTO {

    @NotNull
    @Positive
    private Long id;

    @NotBlank(message = "El nombre del programa es obligatorio")
    @Size(min = 2, max = 20)
    private String nombrePrograma;

    @NotBlank(message = "El nombre del programa es obligatorio")
    @Size(min = 2, max = 550)
    private String nombreEncargado;

    @NotBlank(message = "El tipo es obligatorio")
    private String tipoPrograma;

    @NotBlank(message = "El lugar es obligatorio")
    private String lugarPrograma;

    @NotNull
    private LocalDate fechaPrograma;

}
