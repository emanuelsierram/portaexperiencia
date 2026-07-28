package com.portex.miantorcha.infraestructura.controlador.comando.estudio;

import com.portex.compartido.aplicacion.ComandoRespuesta;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estudios-biblicos")
public class ComandoControladorEstudioBiblico {

    private final ManejadorCrearEstudioBiblico manejadorCrearEstudioBiblico;
    private final ManejadorActualizarEstudioBiblico manejadorActualizarEstudioBiblico;
    private final ManejadorEliminarEstudioBiblico manejadorEliminarEstudioBiblico;

    private final ManejadorRegistrarLeccion manejadorRegistrarLeccion;

    public ComandoControladorEstudioBiblico(ManejadorCrearEstudioBiblico manejadorCrearEstudioBiblico, ManejadorActualizarEstudioBiblico manejadorActualizarEstudioBiblico, ManejadorEliminarEstudioBiblico manejadorEliminarEstudioBiblico, ManejadorRegistrarLeccion manejadorRegistrarLeccion) {
        this.manejadorCrearEstudioBiblico = manejadorCrearEstudioBiblico;
        this.manejadorActualizarEstudioBiblico = manejadorActualizarEstudioBiblico;
        this.manejadorEliminarEstudioBiblico = manejadorEliminarEstudioBiblico;
        this.manejadorRegistrarLeccion = manejadorRegistrarLeccion;
    }

    @PostMapping
    public ComandoRespuesta<Long> crear(@RequestBody ComandoEstudioBiblico comando) {
        return this.manejadorCrearEstudioBiblico.ejecutar(comando);
    }

    @PutMapping("/{id}")
    public void actualizar(@RequestBody ComandoEstudioBiblico comando, @PathVariable Long id) {
        this.manejadorActualizarEstudioBiblico.ejecutar(comando, id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        this.manejadorEliminarEstudioBiblico.ejecutar(id);
    }

    @PostMapping("/lecciones")
    public ComandoRespuesta<Long> registrarLeccion(@RequestBody ComandoHistoricoLeccion comando) {
        return this.manejadorRegistrarLeccion.ejecutar(comando);
    }
}