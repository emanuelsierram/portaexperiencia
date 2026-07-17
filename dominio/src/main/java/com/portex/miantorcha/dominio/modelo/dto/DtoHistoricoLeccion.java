package com.portex.miantorcha.dominio.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoHistoricoLeccion {
    private Long id;
    private Integer contadorSemana;
    private Long idEstudioBiblico;
    private LocalDateTime fechaEstudio;
    private Long idActividad;
}