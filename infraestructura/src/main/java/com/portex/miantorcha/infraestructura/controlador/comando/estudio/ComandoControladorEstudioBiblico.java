package com.portex.miantorcha.infraestructura.controlador.comando.estudio;

import com.portex.compartido.aplicacion.ComandoRespuesta;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estudios-biblicos")
public class ComandoControladorEstudioBiblico {

    private final ManejadorCrearEstudioBiblico manejadorCrearEstudioBiblico;

    public ComandoControladorEstudioBiblico(ManejadorCrearEstudioBiblico manejadorCrearEstudioBiblico) {
        this.manejadorCrearEstudioBiblico = manejadorCrearEstudioBiblico;
    }

    @PostMapping
    public ComandoRespuesta<Long> crear(@RequestBody ComandoEstudioBiblico comando) {
        return this.manejadorCrearEstudioBiblico.ejecutar(comando);
    }
}