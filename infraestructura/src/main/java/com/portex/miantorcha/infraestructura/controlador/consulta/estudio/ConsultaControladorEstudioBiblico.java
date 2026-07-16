package com.portex.miantorcha.infraestructura.controlador.consulta.estudio;

import com.portex.miantorcha.dominio.modelo.dto.DtoEstudioBiblico;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudios-biblicos")
public class ConsultaControladorEstudioBiblico {

    private final ManejadorListarEstudioBiblico manejadorListarEstudioBiblico;

    public ConsultaControladorEstudioBiblico(ManejadorListarEstudioBiblico manejadorListarEstudioBiblico) {
        this.manejadorListarEstudioBiblico = manejadorListarEstudioBiblico;
    }

    @GetMapping("/actuales/{idUsuarioAsignado}")
    public List<DtoEstudioBiblico> consultarActualesPorMiembro(@PathVariable Long idUsuarioAsignado) {
        return this.manejadorListarEstudioBiblico.consultarActualesPorMiembro(idUsuarioAsignado);
    }

    @GetMapping("/disponibles")
    public List<DtoEstudioBiblico> consultarDisponiblesPorDar() {
        return this.manejadorListarEstudioBiblico.consultarDisponiblesPorDar();
    }
}