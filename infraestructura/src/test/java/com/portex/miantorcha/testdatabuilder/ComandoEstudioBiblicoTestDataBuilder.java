package com.portex.miantorcha.testdatabuilder;

import com.portex.miantorcha.infraestructura.controlador.comando.estudio.ComandoEstudioBiblico;
import java.util.UUID;

public class ComandoEstudioBiblicoTestDataBuilder {

    private Long id;
    private String nombrePersona;
    private String telefonoPersona;
    private String direccionPersona;
    private String estado;
    private Long idUsuarioAsignado;
    private String personaQueReporta;
    private Long idGrupo;

    public ComandoEstudioBiblicoTestDataBuilder() {
        this.id = 1L;
        this.nombrePersona = "Pedro Integracion";
        this.telefonoPersona = "3009876543";
        this.direccionPersona = "Avenida Siempre Viva 742";
        this.estado = "por dar";
        this.idUsuarioAsignado = 20L;
        this.personaQueReporta = "Reportador Test";
        this.idGrupo = 5L;
    }

    public ComandoEstudioBiblicoTestDataBuilder conDireccionPersona(String direccionPersona) {
        this.direccionPersona = direccionPersona;
        return this;
    }

    public ComandoEstudioBiblico build() {
        ComandoEstudioBiblico comando = new ComandoEstudioBiblico();
        comando.setId(this.id);
        comando.setNombrePersona(this.nombrePersona);
        comando.setTelefonoPersona(this.telefonoPersona);
        comando.setDireccionPersona(this.direccionPersona);
        comando.setEstado(this.estado);
        comando.setIdUsuarioAsignado(this.idUsuarioAsignado);
        comando.setPersonaQueReporta(this.personaQueReporta);
        comando.setIdGrupo(this.idGrupo);
        return comando;
    }
}