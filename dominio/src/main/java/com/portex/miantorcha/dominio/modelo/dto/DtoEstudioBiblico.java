package com.portex.miantorcha.dominio.modelo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoEstudioBiblico {

    private String id;
    private String nombrePersona;
    private String telefonoPersona;
    private String direccionPersona;
    private String estado;
    private Integer leccion;
    private String idUsuarioAsignado;
    private String personaQueReporta;
    private String idGrupo;

}