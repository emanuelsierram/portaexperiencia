package com.portex.miantorcha.dominio.servicio.estudio;

import com.portex.compartido.dominio.excepcion.ExcepcionSinDatos;
import com.portex.miantorcha.dominio.puerto.dao.DaoEstudioBiblico;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioEstudioBiblico;

public class ServicioEliminarEstudioBiblico {

    private final RepositorioEstudioBiblico repositorioEstudioBiblico;
    private final DaoEstudioBiblico daoEstudioBiblico;

    public ServicioEliminarEstudioBiblico(RepositorioEstudioBiblico repositorioEstudioBiblico, DaoEstudioBiblico daoEstudioBiblico) {
        this.repositorioEstudioBiblico = repositorioEstudioBiblico;
        this.daoEstudioBiblico = daoEstudioBiblico;
    }

    public void ejecutar(Long id) {
        if (this.daoEstudioBiblico.consultarPorId(id) == null) {
            throw new ExcepcionSinDatos("El estudio bíblico que intenta eliminar no existe.");
        }

        this.repositorioEstudioBiblico.eliminar(id);
    }
}