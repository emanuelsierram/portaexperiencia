package com.portex.miantorcha.dominio.puerto.repositorio;

import com.portex.miantorcha.dominio.modelo.entidad.EstudioBiblico;
import com.portex.miantorcha.dominio.modelo.entidad.HistoricoLeccion;

public interface RepositorioEstudioBiblico {

    // Comandos de Escritura (Reciben la Entidad de Dominio)
    void crear(EstudioBiblico estudioBiblico);
    void actualizar(EstudioBiblico estudioBiblico);
    void eliminar(Long id);

    void registrarLeccion(HistoricoLeccion historicoLeccion);
}