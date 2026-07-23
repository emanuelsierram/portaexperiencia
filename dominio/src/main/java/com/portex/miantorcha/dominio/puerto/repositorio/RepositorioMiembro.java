package com.portex.miantorcha.dominio.puerto.repositorio;

import com.portex.miantorcha.dominio.modelo.entidad.Miembro;
import com.portex.miantorcha.dominio.modelo.dto.DtoMiembro;
import java.util.List;

public interface RepositorioMiembro {
    Long crear(Miembro miembro);
    void actualizar(Miembro miembro);
    void eliminar(Long id);
    boolean existeTelefono(String telefono);

}