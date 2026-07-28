package com.portex.miantorcha.infraestructura.controlador.comando.estudio;

import com.portex.compartido.aplicacion.manejador.ManejadorComando;
import com.portex.miantorcha.dominio.servicio.estudio.ServicioEliminarEstudioBiblico;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEliminarEstudioBiblico implements ManejadorComando<Long> {

    private final ServicioEliminarEstudioBiblico servicioEliminarEstudioBiblico;

    public ManejadorEliminarEstudioBiblico(ServicioEliminarEstudioBiblico servicioEliminarEstudioBiblico) {
        this.servicioEliminarEstudioBiblico = servicioEliminarEstudioBiblico;
    }

    @Override
    public void ejecutar(Long idEstudioBiblico) {
        this.servicioEliminarEstudioBiblico.ejecutar(idEstudioBiblico);
    }
}