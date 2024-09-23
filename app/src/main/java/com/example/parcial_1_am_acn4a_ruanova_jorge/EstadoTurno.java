package com.example.parcial_1_am_acn4a_ruanova_jorge;

public enum EstadoTurno {
    A("ACTIVO"),
    C("CANCELADO"),
    F("FINALIZADO");

    private final String descripcion;


    // Constructor
    EstadoTurno(String descripcion) {
        this.descripcion = descripcion;
    }

    // Método para obtener la descripción
    public String getDescripcion() {
        return descripcion;
    }
}
