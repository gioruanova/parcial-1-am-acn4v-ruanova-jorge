package com.example.parcial_1_am_acn4a_ruanova_jorge;

public enum EstadoTurno {
    A("ACTIVO"),
    C("CANCELADO"),
    F("FINALIZADO");

    private final String descripcion;


    EstadoTurno(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
