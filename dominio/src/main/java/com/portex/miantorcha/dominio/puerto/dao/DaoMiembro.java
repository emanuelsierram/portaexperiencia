package com.portex.miantorcha.dominio.puerto.dao;

import com.portex.miantorcha.dominio.modelo.dto.DtoMiembro;

import java.util.List;

public interface DaoMiembro {

    List<DtoMiembro> listar();
    DtoMiembro consultarPorId(Long id);
}
