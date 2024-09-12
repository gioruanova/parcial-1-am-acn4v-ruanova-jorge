package com.example.parcial_1_am_acn4a_ruanova_jorge;

import java.util.ArrayList;

public class RegistroTurnos {

    private ArrayList<TurnoMedico> turnos;

    public RegistroTurnos() {
        turnos = new ArrayList<>();
    }

    public void agregarTurno(TurnoMedico turno) {
        turnos.add(turno);
    }

    public ArrayList<TurnoMedico> obtenerTurnos() {
        return turnos;
    }
}
