package com.example.parcial_1_am_acn4a_ruanova_jorge;

public class UsuarioLogueado {

    private static UsuarioLogueado instance;
    private String nombreUsuario;
    private String dniUsuario;

    private UsuarioLogueado() {
    }

    public static UsuarioLogueado getInstance() {
        if (instance == null) {
            instance = new UsuarioLogueado();
        }
        return instance;
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
}
