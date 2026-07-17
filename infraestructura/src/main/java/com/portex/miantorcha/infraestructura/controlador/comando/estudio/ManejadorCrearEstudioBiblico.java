package com.portex.miantorcha.infraestructura.controlador.comando.estudio;

import com.portex.compartido.aplicacion.ComandoRespuesta;
import com.portex.miantorcha.dominio.servicio.estudio.ServicioCrearEstudioBiblico;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearEstudioBiblico {

    private final FabricaEstudioBiblico fabricaEstudioBiblico;
    private final ServicioCrearEstudioBiblico servicioCrearEstudioBiblico;

    public ManejadorCrearEstudioBiblico(FabricaEstudioBiblico fabricaEstudioBiblico, ServicioCrearEstudioBiblico servicioCrearEstudioBiblico) {
        this.fabricaEstudioBiblico = fabricaEstudioBiblico;
        this.servicioCrearEstudioBiblico = servicioCrearEstudioBiblico;
    }

    public ComandoRespuesta<Long> ejecutar(ComandoEstudioBiblico comando) {
        return new ComandoRespuesta<>(this.servicioCrearEstudioBiblico.ejecutar(this.fabricaEstudioBiblico.crear(comando)));
    }
}