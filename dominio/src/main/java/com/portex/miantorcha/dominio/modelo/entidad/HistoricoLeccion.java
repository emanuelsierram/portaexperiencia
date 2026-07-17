package com.portex.miantorcha.dominio.modelo.entidad;

import com.portex.compartido.dominio.ValidadorArgumento;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class HistoricoLeccion {

    private Long id;
    private Integer contadorSemana;
    private Long idEstudioBiblico;
    private LocalDateTime fechaEstudio;
    private Long idActividad;

    public HistoricoLeccion(Long id, Integer contadorSemana, Long idEstudioBiblico,
                            LocalDateTime fechaEstudio, Long idActividad) {

        ValidadorArgumento.validarObligatorio(contadorSemana, "El contador de la semana es obligatorio");
        ValidadorArgumento.validarObligatorio(idEstudioBiblico, "El ID del estudio bíblico es obligatorio");
        ValidadorArgumento.validarObligatorio(fechaEstudio, "La fecha del estudio es obligatoria");
        ValidadorArgumento.validarObligatorio(idActividad, "El ID de la actividad es obligatorio");

        this.id = id;
        this.contadorSemana = contadorSemana;
        this.idEstudioBiblico = idEstudioBiblico;
        this.fechaEstudio = fechaEstudio;
        this.idActividad = idActividad;
    }
}