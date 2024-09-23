package com.example.parcial_1_am_acn4a_ruanova_jorge;

import android.icu.text.DateFormat;
import android.icu.util.Calendar;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


public class TurnoMedico {

    private static final SecureRandom random = new SecureRandom();

    private final String especialidad;
    private final Date fechaTurno;
    private final String horaTurno;
    private final Usuario usuario;
    private final String id;
    private EstadoTurno  estado;


    public TurnoMedico(Usuario usuario, String especialidad, Date fechaSeleccionada, String horaTurno) {
        this.especialidad = especialidad;
        this.fechaTurno = fechaSeleccionada;
        this.horaTurno = horaTurno;
        this.id = generarNumeroDeIDAleatorio(10000);
        this.usuario = usuario;
        this.estado = EstadoTurno.A;
    }


    public EstadoTurno getEstado() {
        return estado;
    }


    // este es el setter que va a usar el user para cancelar turno, o el medico para marcar como finalizado o cancelado
    public void setEstado(EstadoTurno estado) {
        this.estado = estado;
    }

    public String getEspecialidad() {
        return especialidad;
    }


    public Date getFechaTurno() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaTurno);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


    public String getFechaFormateada() {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
        return dateFormat.format(getFechaTurno());
    }

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
