package com.portex.miantorcha.dominio.servicio.estudio;

import com.portex.compartido.dominio.excepcion.ExcepcionDuplicidad;
import com.portex.miantorcha.dominio.modelo.entidad.EstudioBiblico;
import com.portex.miantorcha.dominio.puerto.dao.DaoEstudioBiblico;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioEstudioBiblico;

public class ServicioCrearEstudioBiblico {

    private static final String EL_TELEFONO_YA_EXISTE = "Ya existe un estudio bíblico registrado con este telefono";

    private final DaoEstudioBiblico daoEstudioBiblico;
    private final RepositorioEstudioBiblico repositorioEstudioBiblico;

    public ServicioCrearEstudioBiblico(DaoEstudioBiblico daoEstudioBiblico, RepositorioEstudioBiblico repositorioEstudioBiblico) {
        this.daoEstudioBiblico = daoEstudioBiblico;
        this.repositorioEstudioBiblico = repositorioEstudioBiblico;
    }

    public Long ejecutar(EstudioBiblico estudioBiblico) {
        if (this.daoEstudioBiblico.existeTelefono(estudioBiblico.getTelefonoPersona())) {
            throw new ExcepcionDuplicidad(EL_TELEFONO_YA_EXISTE);
        }

        estudioBiblico.asignarLeccionCalculada(0);

        return this.repositorioEstudioBiblico.crear(estudioBiblico);
    }
}