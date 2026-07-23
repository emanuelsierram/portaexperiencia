package com.portex.miantorcha.infraestructura.controlador.consulta.miembro;

import com.portex.miantorcha.dominio.modelo.dto.DtoMiembro;
import com.portex.miantorcha.dominio.puerto.dao.DaoMiembro;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarMiembro {

    private final DaoMiembro daoMiembro;

    public ManejadorListarMiembro(DaoMiembro daoMiembro) {
        this.daoMiembro = daoMiembro;
    }

    public List<DtoMiembro> listar() {
        return this.daoMiembro.listar();
    }

    public DtoMiembro consultarPorId(Long id) {
        return this.daoMiembro.consultarPorId(id);
    }
}
