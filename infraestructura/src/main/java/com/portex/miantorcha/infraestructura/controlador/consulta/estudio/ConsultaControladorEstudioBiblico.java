package com.portex.miantorcha.infraestructura.controlador.consulta.estudio;

import com.portex.miantorcha.dominio.modelo.dto.DtoEstudioBiblico;
import com.portex.miantorcha.dominio.modelo.dto.DtoHistoricoLeccion;
import com.portex.miantorcha.dominio.puerto.dao.DaoEstudioBiblico;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioEstudioBiblico;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudios-biblicos")
public class ConsultaControladorEstudioBiblico {

    private final ManejadorListarEstudioBiblico manejadorListarEstudioBiblico;

    private final DaoEstudioBiblico daoEstudioBiblico;


    public ConsultaControladorEstudioBiblico(ManejadorListarEstudioBiblico manejadorListarEstudioBiblico, RepositorioEstudioBiblico repositorioEstudioBiblico, DaoEstudioBiblico daoEstudioBiblico) {
        this.manejadorListarEstudioBiblico = manejadorListarEstudioBiblico;
        this.daoEstudioBiblico = daoEstudioBiblico;
    }

    @GetMapping("/actuales/{idUsuarioAsignado}")
    public List<DtoEstudioBiblico> consultarActualesPorMiembro(@PathVariable Long idUsuarioAsignado) {
        return this.manejadorListarEstudioBiblico.consultarActualesPorMiembro(idUsuarioAsignado);
    }
    @GetMapping("/disponibles")
    public List<DtoEstudioBiblico> consultarDisponiblesPorDar() {
        return this.manejadorListarEstudioBiblico.consultarDisponiblesPorDar();
    }
    @GetMapping("/{idEstudioBiblico}/lecciones")
    public List<DtoHistoricoLeccion> consultarHistoricoLecciones(@PathVariable Long idEstudioBiblico) {
        return this.manejadorListarEstudioBiblico.consultarHistoricoLecciones(idEstudioBiblico);
    }

}