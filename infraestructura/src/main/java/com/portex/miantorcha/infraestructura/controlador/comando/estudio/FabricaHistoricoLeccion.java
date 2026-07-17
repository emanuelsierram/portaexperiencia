package com.portex.miantorcha.infraestructura.controlador.comando.estudio;

import com.portex.miantorcha.dominio.modelo.entidad.HistoricoLeccion;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class FabricaHistoricoLeccion {

    public HistoricoLeccion crear(ComandoHistoricoLeccion comando) {
        return new HistoricoLeccion(
                null,
                comando.getContadorSemana(),
                comando.getIdEstudioBiblico(),
                comando.getFechaEstudio() != null ? comando.getFechaEstudio() : LocalDateTime.now(),
                comando.getIdActividad()
        );
    }
}