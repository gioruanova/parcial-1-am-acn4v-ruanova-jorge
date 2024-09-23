package com.example.parcial_1_am_acn4a_ruanova_jorge;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {
    private String nombreUsuario;
    private String dniUsuario;
    private String contrasenia;
    private boolean isDoctor;
    private String especialidad;


    public Usuario(String nombreUsuario, String contrasenia,String dniUsuario, boolean isDoctor) {
        this.nombreUsuario = nombreUsuario;
        this.dniUsuario = dniUsuario;
        this.isDoctor = isDoctor;
        this.contrasenia=contrasenia;
    }

    public Usuario(String nombreUsuario, String contrasenia,String dniUsuario, boolean isDoctor, String especialidad) {
        this.nombreUsuario = nombreUsuario;
        this.dniUsuario = dniUsuario;
        this.isDoctor = isDoctor;
        this.contrasenia=contrasenia;
        this.especialidad = especialidad;
    }

    public String getContrasenia() {
        return contrasenia;
    }


    public String getNombreUsuario() {
        return nombreUsuario;
    }


    public String getDniUsuario() {
        return dniUsuario;
    }



    public boolean isDoctor() {
        return isDoctor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return isDoctor == usuario.isDoctor && Objects.equals(nombreUsuario, usuario.nombreUsuario) && Objects.equals(dniUsuario, usuario.dniUsuario) && Objects.equals(contrasenia, usuario.contrasenia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreUsuario, dniUsuario, contrasenia, isDoctor);
    }

}
