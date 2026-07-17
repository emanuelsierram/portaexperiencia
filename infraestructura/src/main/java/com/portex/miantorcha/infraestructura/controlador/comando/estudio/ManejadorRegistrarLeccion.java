package com.portex.miantorcha.infraestructura.controlador.comando.estudio;

import com.portex.compartido.aplicacion.ComandoRespuesta;
import com.portex.compartido.dominio.excepcion.ExcepcionSinDatos;
import com.portex.miantorcha.dominio.modelo.dto.DtoEstudioBiblico;
import com.portex.miantorcha.dominio.modelo.entidad.EstudioBiblico;
import com.portex.miantorcha.dominio.modelo.entidad.HistoricoLeccion;
import com.portex.miantorcha.dominio.puerto.dao.DaoEstudioBiblico;
import com.portex.miantorcha.dominio.servicio.estudio.ServicioRegistrarLeccionEstudio;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ManejadorRegistrarLeccion {

    private final ServicioRegistrarLeccionEstudio servicioRegistrarLeccionEstudio;
    private final DaoEstudioBiblico daoEstudioBiblico;
    private final FabricaEstudioBiblico fabricaEstudioBiblico;
    private final FabricaHistoricoLeccion fabricaHistoricoLeccion;

    public ManejadorRegistrarLeccion(ServicioRegistrarLeccionEstudio servicioRegistrarLeccionEstudio,
                                     DaoEstudioBiblico daoEstudioBiblico,
                                     FabricaEstudioBiblico fabricaEstudioBiblico,
                                     FabricaHistoricoLeccion fabricaHistoricoLeccion) {
        this.servicioRegistrarLeccionEstudio = servicioRegistrarLeccionEstudio;
        this.daoEstudioBiblico = daoEstudioBiblico;
        this.fabricaEstudioBiblico = fabricaEstudioBiblico;
        this.fabricaHistoricoLeccion = fabricaHistoricoLeccion;
    }

    @Transactional
    public ComandoRespuesta<Long> ejecutar(ComandoHistoricoLeccion comando) {
        // 1. Recuperamos el estado persistido del estudio asociado
        DtoEstudioBiblico dto = this.daoEstudioBiblico.consultarPorId(comando.getIdEstudioBiblico());
        if (dto == null) {
            throw new ExcepcionSinDatos("El estudio bíblico asociado no existe.");
        }

        // 2. Reutilizamos la FabricaEstudioBiblico convirtiendo el DTO en un Comando para reconstruir la entidad
        ComandoEstudioBiblico comandoEstudio = mapearDtoAComando(dto);
        EstudioBiblico estudioBiblico = this.fabricaEstudioBiblico.crear(comandoEstudio);
        estudioBiblico.asignarLeccionCalculada(dto.getLeccion());

        // 3. Traducimos el comando del histórico usando su fábrica dedicada
        HistoricoLeccion historicoLeccion = this.fabricaHistoricoLeccion.crear(comando);

        // 4. Orquestamos la ejecución atómica de las reglas de dominio
        this.servicioRegistrarLeccionEstudio.ejecutar(historicoLeccion, estudioBiblico);

        // Retornamos la respuesta estandarizada con el ID del estudio afectado
        return new ComandoRespuesta<>(estudioBiblico.getId());
    }

    private ComandoEstudioBiblico mapearDtoAComando(DtoEstudioBiblico dto) {
        ComandoEstudioBiblico comando = new ComandoEstudioBiblico();
        comando.setId(dto.getId());
        comando.setNombrePersona(dto.getNombrePersona());
        comando.setTelefonoPersona(dto.getTelefonoPersona());
        comando.setDireccionPersona(dto.getDireccionPersona());
        comando.setEstado(dto.getEstado());
        comando.setIdUsuarioAsignado(dto.getIdUsuarioAsignado());
        comando.setPersonaQueReporta(dto.getPersonaQueReporta());
        comando.setIdGrupo(dto.getIdGrupo());
        return comando;
    }
}