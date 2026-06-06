package com.example.Envios.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnviosDto {

    @NotNull(message = "El ID de la orden es obligatorio")
    private Long ordenId;

    @NotBlank(message = "La dirección no puede estar vacía")
    private String direccion;

    @NotBlank(message = "El estado no puede estar vacío")
    private String estado;
}