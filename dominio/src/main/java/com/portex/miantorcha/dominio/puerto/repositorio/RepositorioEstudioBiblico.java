package com.portex.miantorcha.dominio.puerto.repositorio;

import com.portex.miantorcha.dominio.modelo.entidad.EstudioBiblico;

public interface RepositorioEstudioBiblico {

    // Comandos de Escritura (Reciben la Entidad de Dominio)
    void crear(EstudioBiblico estudioBiblico);
    void actualizar(EstudioBiblico estudioBiblico);
    void eliminar(String id);

    /**
     * Consulta auxiliar EXCLUSIVA para las reglas de negocio (no para la vista).
     * El Servicio de Dominio la necesita para saber qué número de lección calcular.
     */
    int contarLeccionesPorPersona(String nombrePersona, String telefonoPersona);
}