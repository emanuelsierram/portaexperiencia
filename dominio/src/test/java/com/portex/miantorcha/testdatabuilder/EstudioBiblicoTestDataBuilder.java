package com.portex.miantorcha.testdatabuilder;

import com.portex.miantorcha.dominio.modelo.entidad.EstudioBiblico;

public class EstudioBiblicoTestDataBuilder {

    private Long id;
    private String nombrePersona;
    private String telefonoPersona;
    private String direccionPersona;
    private String estado;
    private Long idUsuarioAsignado;
    private String personaQueReporta;
    private Long idGrupo;

    public EstudioBiblicoTestDataBuilder() {
        this.id = 1L;
        this.nombrePersona = "Juan Perez";
        this.telefonoPersona = "3001234567";
        this.direccionPersona = "Calle Falsa 123";
        this.estado = "por dar";
        this.idUsuarioAsignado = 100L;
        this.personaQueReporta = "Maria Gomez";
        this.idGrupo = 50L;    }

    public EstudioBiblicoTestDataBuilder conNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
        return this;
    }

    public EstudioBiblicoTestDataBuilder conDireccionPersona(String direccionPersona) {
        this.direccionPersona = direccionPersona;
        return this;
    }

    public EstudioBiblicoTestDataBuilder conEstado(String estado) {
        this.estado = estado;
        return this;
    }

    public EstudioBiblico build() {
        return new EstudioBiblico(id, nombrePersona, telefonoPersona, direccionPersona,
                estado, idUsuarioAsignado, personaQueReporta, idGrupo);
    }
}