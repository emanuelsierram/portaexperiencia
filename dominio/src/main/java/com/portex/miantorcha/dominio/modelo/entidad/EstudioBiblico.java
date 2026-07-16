package com.portex.miantorcha.dominio.modelo.entidad;

import com.portex.compartido.dominio.ValidadorArgumento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstudioBiblico {

    private final String id;
    private final String nombrePersona;
    private final String telefonoPersona;
    private final String direccionPersona;
    private final String estado;
    private Integer leccion; // Campo calculado (1-20)
    private final String idUsuarioAsignado;
    private final String personaQueReporta;
    private final String idGrupo;

    public EstudioBiblico(String id, String nombrePersona, String telefonoPersona,
                          String direccionPersona, String estado,
                          String idUsuarioAsignado, String personaQueReporta,
                          String idGrupo) {

        // Reglas de negocio: Validaciones obligatorias
        ValidadorArgumento.validarObligatorio(nombrePersona, "El nombre de la persona es obligatorio");
        ValidadorArgumento.validarObligatorio(direccionPersona, "La dirección de la persona es obligatoria");

        this.id = id;
        this.nombrePersona = nombrePersona;
        this.telefonoPersona = telefonoPersona;
        this.direccionPersona = direccionPersona;
        this.estado = EstadoEstudioBiblico.desdeValor(estado).getValor();
        this.idUsuarioAsignado = idUsuarioAsignado;
        this.personaQueReporta = personaQueReporta;
        this.idGrupo = idGrupo;
    }

    // Método para inyectar el valor calculado de la lección tras aplicar la lógica
    public void asignarLeccionCalculada(Integer leccionCalculada) {
        ValidadorArgumento.validarObligatorio(leccionCalculada, "La lección calculada no puede ser nula");
        if (leccionCalculada < 1 || leccionCalculada > 20) {
            throw new IllegalArgumentException("La lección debe estar entre 1 y 20");
        }
        this.leccion = leccionCalculada;
    }

}