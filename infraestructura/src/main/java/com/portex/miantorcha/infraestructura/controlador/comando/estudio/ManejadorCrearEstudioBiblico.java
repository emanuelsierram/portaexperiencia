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

    public ComandoRespuesta<String> ejecutar(ComandoEstudioBiblico comando) {
        // Ejecutamos la lógica de negocio y persistencia
        this.servicioCrearEstudioBiblico.ejecutar(this.fabricaEstudioBiblico.crear(comando));

        // Retornamos la respuesta estándar de tu arquitectura
        return new ComandoRespuesta<>(comando.getId());
    }
}