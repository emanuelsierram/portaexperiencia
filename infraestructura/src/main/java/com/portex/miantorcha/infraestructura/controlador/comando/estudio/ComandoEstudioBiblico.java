package com.portex.miantorcha.infraestructura.controlador.comando.estudio;

public class ComandoEstudioBiblico {
    private String id;
    private String nombrePersona;
    private String telefonoPersona;
    private String direccionPersona;
    private String estado;
    private String idUsuarioAsignado;
    private String personaQueReporta;
    private String idGrupo;

    // Getters y Setters necesarios para que Spring Boot mapee el JSON
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombrePersona() { return nombrePersona; }
    public void setNombrePersona(String nombrePersona) { this.nombrePersona = nombrePersona; }
    public String getTelefonoPersona() { return telefonoPersona; }
    public void setTelefonoPersona(String telefonoPersona) { this.telefonoPersona = telefonoPersona; }
    public String getDireccionPersona() { return direccionPersona; }
    public void setDireccionPersona(String direccionPersona) { this.direccionPersona = direccionPersona; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getIdUsuarioAsignado() { return idUsuarioAsignado; }
    public void setIdUsuarioAsignado(String idUsuarioAsignado) { this.idUsuarioAsignado = idUsuarioAsignado; }
    public String getPersonaQueReporta() { return personaQueReporta; }
    public void setPersonaQueReporta(String personaQueReporta) { this.personaQueReporta = personaQueReporta; }
    public String getIdGrupo() { return idGrupo; }
    public void setIdGrupo(String idGrupo) { this.idGrupo = idGrupo; }
}