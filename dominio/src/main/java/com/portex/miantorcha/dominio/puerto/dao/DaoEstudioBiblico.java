package com.portex.miantorcha.dominio.puerto.dao;

import com.portex.miantorcha.dominio.modelo.dto.DtoEstudioBiblico;
import com.portex.miantorcha.dominio.modelo.dto.DtoHistoricoLeccion;
import com.portex.miantorcha.dominio.modelo.entidad.HistoricoLeccion;

import java.util.List;

public interface DaoEstudioBiblico {

    /**
     * HU: "mirar mi estudio biblico actual"
     * Devuelve los estudios bíblicos asignados a un miembro (usuario) específico como DTOs.
     */
    List<DtoEstudioBiblico> consultarActualesPorMiembro(Long idUsuarioAsignado);

    /**
     * HU: "mirar todos los estudios bíblicos disponibles con estado por hacer (por dar)"
     * Devuelve la lista de DTOs de estudios que aún no han comenzado y están disponibles.
     */
    List<DtoEstudioBiblico> consultarDisponiblesPorDar();

    /**
     * Consulta general de solo lectura.
     */
    DtoEstudioBiblico consultarPorId(Long id);

    List<DtoHistoricoLeccion> consultarHistoricoPorEstudio(Long idEstudioBiblico);
}