package com.example.Ordenes.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrdenesDto {

    @NotNull(message = "El ID de usuario es obligatorio")
    private Long usuarioId;

    @NotNull(message = "El total de la orden no puede ser nulo")
    @Min(value = 0, message = "El total no puede ser negativo")
    private Integer total;

    @NotBlank(message = "El estado de la orden no puede estar vacío")
    private String estado;
}