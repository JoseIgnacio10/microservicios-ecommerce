package com.example.Pagos.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PagosDto {

    @NotNull(message = "El ID de la orden es obligatorio")
    private Long ordenId;

    @NotBlank(message = "El método de pago no puede estar vacío")
    private String metodoPago;

    @NotBlank(message = "El estado del pago no puede estar vacío")
    private String estado;
}