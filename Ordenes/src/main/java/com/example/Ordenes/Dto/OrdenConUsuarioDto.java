package com.example.Ordenes.Dto;

import lombok.Data;

@Data
public class OrdenConUsuarioDto {

    private Long id;
    private Long usuarioId;
    private Integer total;
    private String estado;
    private UsuarioJsonDto usuario;

}