package com.portex.miantorcha.infraestructura.controlador.comando.estudio;

import com.portex.miantorcha.dominio.modelo.entidad.EstudioBiblico;
import com.portex.miantorcha.dominio.servicio.estudio.ServicioActualizarEstudioBiblico;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarEstudioBiblico {

    private final ServicioActualizarEstudioBiblico servicioActualizarEstudioBiblico;
    private final FabricaEstudioBiblico fabricaEstudioBiblico;

    public ManejadorActualizarEstudioBiblico(ServicioActualizarEstudioBiblico servicioActualizarEstudioBiblico, FabricaEstudioBiblico fabricaEstudioBiblico) {
        this.servicioActualizarEstudioBiblico = servicioActualizarEstudioBiblico;
        this.fabricaEstudioBiblico = fabricaEstudioBiblico;
    }

    public void ejecutar(ComandoEstudioBiblico comando, Long id) {
        comando.setId(id);
        EstudioBiblico estudioBiblico = this.fabricaEstudioBiblico.crear(comando);
        this.servicioActualizarEstudioBiblico.ejecutar(estudioBiblico);
    }
}