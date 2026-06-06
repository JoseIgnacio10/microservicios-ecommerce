package com.example.Ordenes.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ordenes") // Conecta directo con tu tabla en MySQL
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(nullable = false)
    private Integer total;

    @Column(nullable = false, length = 30)
    private String estado;
}