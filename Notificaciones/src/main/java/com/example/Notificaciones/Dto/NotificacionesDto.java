package com.example.Notificaciones.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NotificacionesDto {

    @NotNull(message = "El ID de usuario es obligatorio")
    private Long usuarioId;

    @NotBlank(message = "El tipo de notificación (EMAIL/SMS) no puede estar vacío")
    private String tipo;

    @NotBlank(message = "El mensaje no puede estar vacío")
    private String mensaje;
}