package com.example.parcial_1_am_acn4a_ruanova_jorge;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class ListadoUsuarios {

    final private List<Usuario> listaUsuarios;

    public ListadoUsuarios() {
        listaUsuarios = new ArrayList<>();
    }

    public void agregarUsuario(Usuario usuario) {
        listaUsuarios.add(usuario);
    }

    public List<Usuario> getUsuarios() {
        return listaUsuarios;
    }


    public Usuario validarPerfil(String email) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getEmailUsuario().equals(email)) {
                return usuario;
            }
        }
        // user no existe, no dar pistas
        return null;
    }


}
