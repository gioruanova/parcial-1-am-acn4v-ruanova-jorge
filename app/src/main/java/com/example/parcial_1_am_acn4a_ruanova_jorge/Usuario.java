package com.example.parcial_1_am_acn4a_ruanova_jorge;

public class Usuario {
    private static UsuarioLogueado instance;
    private String nombreUsuario;
    private String dniUsuario;
    private String contrasenia;
    private boolean isDoctor;


    public Usuario(String nombreUsuario, String contrasenia,String dniUsuario, boolean isDoctor) {
        this.nombreUsuario = nombreUsuario;
        this.dniUsuario = dniUsuario;
        this.isDoctor = isDoctor;
        this.contrasenia=contrasenia;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public static UsuarioLogueado getInstance() {
        return instance;
    }

    public static void setInstance(UsuarioLogueado instance) {
        Usuario.instance = instance;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getDniUsuario() {
        return dniUsuario;
    }

    public void setDniUsuario(String dniUsuario) {
        this.dniUsuario = dniUsuario;
    }

    public boolean isDoctor() {
        return isDoctor;
    }



    public void setDoctor(boolean doctor) {
        isDoctor = doctor;
    }
}
