package com.portex.miantorcha.dominio.modelo.entidad;

public enum EstadoEstudioBiblico {
    POR_DAR("por dar"),
    EN_CURSO("en curso"),
    COMPLETADO("completado");

    private final String valor;

    EstadoEstudioBiblico(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static EstadoEstudioBiblico desdeValor(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return POR_DAR;
        }

        for (EstadoEstudioBiblico estado : EstadoEstudioBiblico.values()) {
            if (estado.getValor().equalsIgnoreCase(valor.trim())) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado de estudio bíblico no válido: " + valor);
    }
}