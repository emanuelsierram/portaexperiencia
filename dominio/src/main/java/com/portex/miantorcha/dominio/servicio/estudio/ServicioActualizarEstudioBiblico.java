package com.portex.miantorcha.dominio.servicio.estudio;

import com.portex.compartido.dominio.excepcion.ExcepcionSinDatos;
import com.portex.miantorcha.dominio.modelo.entidad.EstudioBiblico;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioEstudioBiblico;
import com.portex.miantorcha.dominio.puerto.dao.DaoEstudioBiblico;

public class ServicioActualizarEstudioBiblico {

    private final RepositorioEstudioBiblico repositorioEstudioBiblico;
    private final DaoEstudioBiblico daoEstudioBiblico;

    public ServicioActualizarEstudioBiblico(RepositorioEstudioBiblico repositorioEstudioBiblico, DaoEstudioBiblico daoEstudioBiblico) {
        this.repositorioEstudioBiblico = repositorioEstudioBiblico;
        this.daoEstudioBiblico = daoEstudioBiblico;
    }

    public void ejecutar(EstudioBiblico estudioBiblico) {
        if (this.daoEstudioBiblico.consultarPorId(estudioBiblico.getId()) == null) {
            throw new ExcepcionSinDatos("El estudio bíblico que intenta actualizar no existe.");
        }
        this.repositorioEstudioBiblico.actualizar(estudioBiblico);
    }
}