package com.example.parcial_1_am_acn4a_ruanova_jorge;

import java.security.SecureRandom;
import java.util.Objects;


public class TurnoMedico {

    private static final SecureRandom random = new SecureRandom();

    private String especialidad;
    private String fechaTurno;
    private String horaTurno;
    private String usuario;
    private String id;



    public TurnoMedico() {
    }

    public TurnoMedico(String usuario, String especialidad, String fechaTurno, String horaTurno) {
        this.especialidad = especialidad;
        this.fechaTurno = fechaTurno;
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
        return fechaTurno;
    }

    public void setFechaTurno(String fechaTurno) {
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
