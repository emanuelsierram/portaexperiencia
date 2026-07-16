package com.portex.miantorcha.dominio.servicio.estudio;

import com.portex.compartido.dominio.excepcion.ExcepcionValorInvalido;
import com.portex.miantorcha.dominio.modelo.entidad.EstudioBiblico;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioEstudioBiblico;

public class ServicioCrearEstudioBiblico {

    private static final int MAX_LECCIONES = 20;
    private final RepositorioEstudioBiblico repositorioEstudioBiblico;

    // Inyección de dependencias a través del constructor
    public ServicioCrearEstudioBiblico(RepositorioEstudioBiblico repositorioEstudioBiblico) {
        this.repositorioEstudioBiblico = repositorioEstudioBiblico;
    }

    public void ejecutar(EstudioBiblico estudioBiblico) {
        // 1. Consultar el histórico: ¿Cuántas lecciones tiene esta persona registradas?
        int leccionesExistentes = this.repositorioEstudioBiblico.contarLeccionesPorPersona(
                estudioBiblico.getNombrePersona(),
                estudioBiblico.getTelefonoPersona()
        );

        // 2. Lógica de negocio: Calcular el número de la nueva lección
        int nuevaLeccion = leccionesExistentes + 1;

        // 3. Validación de dominio: No puede haber más de 20 lecciones
        if (nuevaLeccion > MAX_LECCIONES) {
            throw new ExcepcionValorInvalido("La persona ya ha completado el máximo de 20 lecciones permitidas.");
        }

        // 4. Asignar el valor calculado a la entidad blindada
        estudioBiblico.asignarLeccionCalculada(nuevaLeccion);

        // 5. Delegar la persistencia al puerto (la interfaz)
        this.repositorioEstudioBiblico.crear(estudioBiblico);
    }
}