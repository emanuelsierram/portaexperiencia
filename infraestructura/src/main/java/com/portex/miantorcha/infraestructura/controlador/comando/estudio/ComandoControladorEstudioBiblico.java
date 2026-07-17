package com.portex.miantorcha.infraestructura.controlador.comando.estudio;

import com.portex.compartido.aplicacion.ComandoRespuesta;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estudios-biblicos")
public class ComandoControladorEstudioBiblico {

    private final ManejadorCrearEstudioBiblico manejadorCrearEstudioBiblico;
    private final ManejadorActualizarEstudioBiblico manejadorActualizarEstudioBiblico;

    public ComandoControladorEstudioBiblico(ManejadorCrearEstudioBiblico manejadorCrearEstudioBiblico, ManejadorActualizarEstudioBiblico manejadorActualizarEstudioBiblico) {
        this.manejadorCrearEstudioBiblico = manejadorCrearEstudioBiblico;
        this.manejadorActualizarEstudioBiblico = manejadorActualizarEstudioBiblico;
    }

    @PostMapping
    public ComandoRespuesta<Long> crear(@RequestBody ComandoEstudioBiblico comando) {
        return this.manejadorCrearEstudioBiblico.ejecutar(comando);
    }

    @PutMapping("/{id}")
    public void actualizar(@RequestBody ComandoEstudioBiblico comando, @PathVariable Long id) {
        this.manejadorActualizarEstudioBiblico.ejecutar(comando, id);
    }
}