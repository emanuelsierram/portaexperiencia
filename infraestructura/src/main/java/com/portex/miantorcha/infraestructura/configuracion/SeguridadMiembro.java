package com.portex.miantorcha.infraestructura.configuracion;

import com.portex.miantorcha.dominio.modelo.dto.DtoMiembro;
import com.portex.miantorcha.dominio.puerto.dao.DaoMiembro;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class SeguridadMiembro {

    private final DaoMiembro daoMiembro;

    public SeguridadMiembro(DaoMiembro daoMiembro) {
        this.daoMiembro = daoMiembro;
    }

    public boolean puedeConsultarPorId(Long id, Authentication authentication) {
        if (id == null || authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        if (authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
            return true;
        }

        DtoMiembro miembro = this.daoMiembro.consultarPorId(id);
        return miembro != null
                && miembro.getUsuarioId() != null
                && miembro.getUsuarioId().equals(authentication.getName());
    }
}
