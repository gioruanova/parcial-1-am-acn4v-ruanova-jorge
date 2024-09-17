package com.example.parcial_1_am_acn4a_ruanova_jorge;

import android.annotation.SuppressLint;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class TurnoMedico {

    private static final SecureRandom random = new SecureRandom();

    private String especialidad;
    private Date fechaTurno;
    private String horaTurno;
    private String usuario;
    private String id;


    public TurnoMedico() {

    }

    public TurnoMedico(String usuario, String especialidad, Date fechaSeleccionada, String horaTurno) {
        this.especialidad = especialidad;
        this.fechaTurno = fechaSeleccionada;
        this.horaTurno = horaTurno;
        this.id = generarNumeroDeIDAleatorio(10000);
        this.usuario = usuario;
    }


    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getFechaTurno() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fechaTurno);    }

    public void setFechaTurno(Date fechaTurno) {
        this.fechaTurno = fechaTurno;
    }

    public String getHoraTurno() {
        return horaTurno;
    }

    public void setHoraTurno(String horaTurno) {
        this.horaTurno = horaTurno;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
        return Objects.equals(especialidad, that.especialidad) && Objects.equals(fechaTurno, that.fechaTurno) && Objects.equals(horaTurno, that.horaTurno) && Objects.equals(usuario, that.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(especialidad, fechaTurno, horaTurno, usuario);
    }


}
