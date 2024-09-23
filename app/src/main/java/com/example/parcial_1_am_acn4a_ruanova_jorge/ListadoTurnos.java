package com.example.parcial_1_am_acn4a_ruanova_jorge;

import java.util.ArrayList;

    //singleton de turnos para simular conexion y fetch con db
public class ListadoTurnos {

    private static ListadoTurnos instancia;

    private final ArrayList<TurnoMedico> turnos;

    private ListadoTurnos() {
        turnos = new ArrayList<>();
    }

    public static synchronized ListadoTurnos obtenerInstancia() {
        if (instancia == null) {
            instancia = new ListadoTurnos();
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
