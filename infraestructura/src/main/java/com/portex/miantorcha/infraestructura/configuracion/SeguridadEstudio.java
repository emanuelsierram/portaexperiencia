package com.portex.miantorcha.infraestructura.configuracion;

import com.portex.miantorcha.dominio.modelo.dto.DtoEstudioBiblico;
import com.portex.miantorcha.dominio.modelo.dto.DtoMiembro;
import com.portex.miantorcha.dominio.puerto.dao.DaoMiembro;
import com.portex.miantorcha.dominio.puerto.dao.DaoEstudioBiblico;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class SeguridadEstudio {

    private final DaoEstudioBiblico daoEstudioBiblico;
    private final DaoMiembro daoMiembro;

    public SeguridadEstudio(DaoEstudioBiblico daoEstudioBiblico, DaoMiembro daoMiembro) {
        this.daoEstudioBiblico = daoEstudioBiblico;
        this.daoMiembro = daoMiembro;
    }

    public boolean puedeConsultarLeccionesPorEstudio(Long idEstudioBiblico, Authentication authentication) {

        if (idEstudioBiblico == null || authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        if (authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
            return true;
        }


        DtoEstudioBiblico estudioBiblico = this.daoEstudioBiblico.consultarPorId(idEstudioBiblico);
    DtoMiembro miembro = this.daoMiembro.consultarPorUsuarioId(authentication.getName());
        return estudioBiblico != null
                && estudioBiblico.getIdUsuarioAsignado() != null
        && miembro != null
        && estudioBiblico.getIdUsuarioAsignado().equals(miembro.getId());
    }
}