package com.example.parcial_1_am_acn4a_ruanova_jorge;

import android.annotation.SuppressLint;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class TurnoMedico {

    private static final SecureRandom random = new SecureRandom();

    private final String especialidad;
    private final Date fechaTurno;
    private final String horaTurno;
    private final Usuario usuario;
    private final String id;
    private boolean estado;


    public TurnoMedico(Usuario usuario, String especialidad, Date fechaSeleccionada, String horaTurno) {
        this.especialidad = especialidad;
        this.fechaTurno = fechaSeleccionada;
        this.horaTurno = horaTurno;
        this.id = generarNumeroDeIDAleatorio(10000);
        this.usuario = usuario;
        this.estado = true;
    }


    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getEspecialidad() {
        return especialidad;
    }


    public String getFechaTurno() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fechaTurno);    }

    public String getHoraTurno() {
        return horaTurno;
    }


    public Usuario getUsuario() {
        return usuario;
    }


    public String getId() {
        return id;
    }

    public static String generarNumeroDeIDAleatorio(int limite) {
        int numeroAleatorio = random.nextInt(limite);
        return Integer.toString(numeroAleatorio);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TurnoMedico)) return false;
        TurnoMedico that = (TurnoMedico) o;
        return Objects.equals(usuario, that.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(especialidad, fechaTurno, horaTurno, usuario);
    }


}
