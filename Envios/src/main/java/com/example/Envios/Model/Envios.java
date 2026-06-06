package com.example.Envios.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Envios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Envios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "orden_id", nullable = false)
    private Long ordenId;

    @Column(nullable = false, length = 255)
    private String direccion;

    @Column(nullable = false, length = 30)
    private String estado;
}
