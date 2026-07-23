package com.portex.miantorcha.infraestructura.controlador.consulta.miembro;

import com.portex.miantorcha.dominio.modelo.dto.DtoMiembro;
import com.portex.miantorcha.infraestructura.configuracion.SeguridadMiembro;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/miembros")
public class ConsultaControladorMiembro {

    private final ManejadorListarMiembro manejadorListarMiembro;
    private final SeguridadMiembro seguridadMiembro;

    public ConsultaControladorMiembro(ManejadorListarMiembro manejadorListarMiembro, SeguridadMiembro seguridadMiembro) {
        this.manejadorListarMiembro = manejadorListarMiembro;
        this.seguridadMiembro = seguridadMiembro;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<DtoMiembro>> listar() {
        return ResponseEntity.ok(this.manejadorListarMiembro.listar());
    }

    @GetMapping("/{id}")
    @PreAuthorize("@seguridadMiembro.puedeConsultarPorId(#id, authentication)")
    public ResponseEntity<DtoMiembro> consultarPorId(@PathVariable Long id, Authentication authentication) {
        DtoMiembro miembro = this.manejadorListarMiembro.consultarPorId(id);
        return miembro != null ? ResponseEntity.ok(miembro) : ResponseEntity.notFound().build();
    }
}
