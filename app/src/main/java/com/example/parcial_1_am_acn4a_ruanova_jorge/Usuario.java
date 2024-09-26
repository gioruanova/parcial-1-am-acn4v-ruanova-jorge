package com.example.parcial_1_am_acn4a_ruanova_jorge;

import java.io.Serializable;
import java.util.Objects;


//hacer esta clase abstract y generar una subclase paciente, admin y doctor (va a escalar mal el programa sino)
public class Usuario implements Serializable {
    private String nombreUsuario;

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    private String dniUsuario;
    private String contrasenia;
    private boolean isDoctor;
    private String especialidad;
    private String emailUsuario;

    public Usuario(String nombreUsuario, String contrasenia,String dniUsuario, boolean isDoctor,String emailUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.dniUsuario = dniUsuario;
        this.isDoctor = isDoctor;
        this.contrasenia=contrasenia;
        this.emailUsuario=emailUsuario;
    }

    public Usuario(String nombreUsuario, String contrasenia, String dniUsuario, boolean isDoctor, String especialidad, String emailUsuario) {
        this(nombreUsuario, contrasenia, dniUsuario, isDoctor, emailUsuario);
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
