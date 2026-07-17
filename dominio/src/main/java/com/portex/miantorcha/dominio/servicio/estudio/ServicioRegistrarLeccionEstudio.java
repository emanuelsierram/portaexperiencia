package com.portex.miantorcha.dominio.servicio.estudio;

import com.portex.compartido.dominio.excepcion.ExcepcionSinDatos;
import com.portex.miantorcha.dominio.modelo.entidad.EstudioBiblico;
import com.portex.miantorcha.dominio.modelo.entidad.HistoricoLeccion;
import com.portex.miantorcha.dominio.puerto.dao.DaoEstudioBiblico;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioEstudioBiblico;

public class ServicioRegistrarLeccionEstudio {

    private final RepositorioEstudioBiblico repositorioEstudioBiblico;
    private final DaoEstudioBiblico daoEstudioBiblico;

    public ServicioRegistrarLeccionEstudio(RepositorioEstudioBiblico repositorioEstudioBiblico, DaoEstudioBiblico daoEstudioBiblico) {
        this.repositorioEstudioBiblico = repositorioEstudioBiblico;
        this.daoEstudioBiblico = daoEstudioBiblico;
    }

    public void ejecutar(HistoricoLeccion historicoLeccion, EstudioBiblico estudioBiblico) {
        if (this.daoEstudioBiblico.consultarPorId(estudioBiblico.getId()) == null) {
            throw new ExcepcionSinDatos("El estudio bíblico asociado a la lección no existe.");
        }

        int leccionActual = estudioBiblico.getLeccion() != null ? estudioBiblico.getLeccion() : 0;
        int nuevaLeccion = leccionActual + 1;

        estudioBiblico.asignarLeccionCalculada(nuevaLeccion);

        this.repositorioEstudioBiblico.registrarLeccion(historicoLeccion);
        this.repositorioEstudioBiblico.actualizar(estudioBiblico);
    }
}