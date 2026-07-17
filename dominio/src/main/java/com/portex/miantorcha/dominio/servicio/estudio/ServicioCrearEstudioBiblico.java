package com.portex.miantorcha.dominio.servicio.estudio;

import com.portex.miantorcha.dominio.modelo.entidad.EstudioBiblico;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioEstudioBiblico;

public class ServicioCrearEstudioBiblico {

    private final RepositorioEstudioBiblico repositorioEstudioBiblico;

    public ServicioCrearEstudioBiblico(RepositorioEstudioBiblico repositorioEstudioBiblico) {
        this.repositorioEstudioBiblico = repositorioEstudioBiblico;
    }

    public Long ejecutar(EstudioBiblico estudioBiblico) {
        estudioBiblico.asignarLeccionCalculada(0);

        return this.repositorioEstudioBiblico.crear(estudioBiblico);
    }
}