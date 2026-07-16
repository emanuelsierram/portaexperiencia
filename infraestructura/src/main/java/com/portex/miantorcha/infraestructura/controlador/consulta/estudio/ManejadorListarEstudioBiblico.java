package com.portex.miantorcha.infraestructura.controlador.consulta.estudio;

import com.portex.miantorcha.dominio.modelo.dto.DtoEstudioBiblico;
import com.portex.miantorcha.dominio.puerto.dao.DaoEstudioBiblico;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarEstudioBiblico {

    private final DaoEstudioBiblico daoEstudioBiblico;

    public ManejadorListarEstudioBiblico(DaoEstudioBiblico daoEstudioBiblico) {
        this.daoEstudioBiblico = daoEstudioBiblico;
    }

    // Consulta: "mirar mi estudio biblico actual"
    public List<DtoEstudioBiblico> consultarActualesPorMiembro(Long idUsuarioAsignado) {
        return this.daoEstudioBiblico.consultarActualesPorMiembro(idUsuarioAsignado);
    }

    // Consulta: "mirar todos los estudios bíblicos disponibles con estado por hacer (por dar)"
    public List<DtoEstudioBiblico> consultarDisponiblesPorDar() {
        return this.daoEstudioBiblico.consultarDisponiblesPorDar();
    }
}