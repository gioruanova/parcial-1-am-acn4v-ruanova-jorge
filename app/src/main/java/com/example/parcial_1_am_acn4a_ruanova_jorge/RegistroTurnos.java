package com.example.parcial_1_am_acn4a_ruanova_jorge;

import java.util.ArrayList;

public class RegistroTurnos {

    private static RegistroTurnos instancia;

    private final ArrayList<TurnoMedico> turnos;

    private RegistroTurnos() {
        turnos = new ArrayList<>();
    }

    public static synchronized RegistroTurnos obtenerInstancia() {
        if (instancia == null) {
            instancia = new RegistroTurnos();
        }
        return instancia;
    }

    public void agregarTurno(TurnoMedico turno) {
        turnos.add(turno);
    }

    public ArrayList<TurnoMedico> obtenerTurnos() {
        return turnos;
    }
}
