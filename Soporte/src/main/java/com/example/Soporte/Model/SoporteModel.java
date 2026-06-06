package com.example.Soporte.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "soporte") // Conecta a la tabla de tickets en tu MySQL
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoporteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(nullable = false, length = 100)
    private String asunto;

    @Column(nullable = false, length = 1000)
    private String descripcion;

    @Column(nullable = false, length = 30)
    private String estado; // Ejemplo: ABIERTO, EN_PROCESO, RESUELTO
}