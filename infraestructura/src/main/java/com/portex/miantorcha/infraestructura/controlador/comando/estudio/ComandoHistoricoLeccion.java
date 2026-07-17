package com.portex.miantorcha.infraestructura.controlador.comando.estudio;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class ComandoHistoricoLeccion {
    private Integer contadorSemana;
    private Long idEstudioBiblico;
    private LocalDateTime fechaEstudio;
    private Long idActividad;
}