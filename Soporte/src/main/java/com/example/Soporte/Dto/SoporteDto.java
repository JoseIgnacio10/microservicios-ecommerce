package com.example.Soporte.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SoporteDto {

    @NotNull(message = "El ID de usuario es obligatorio")
    private Long usuarioId;

    @NotBlank(message = "El asunto no puede estar vacío")
    private String asunto;

    @NotBlank(message = "La descripción del problema es obligatoria")
    private String descripcion;

    @NotBlank(message = "El estado del ticket es obligatorio")
    private String estado;
}