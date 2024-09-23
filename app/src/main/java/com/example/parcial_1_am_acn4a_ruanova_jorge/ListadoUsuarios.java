package com.example.parcial_1_am_acn4a_ruanova_jorge;

import java.util.ArrayList;
import java.util.List;


// Creacion generica de usuarios para tener interactividad
public class ListadoUsuarios {

    final private List<Usuario> listaUsuarios;

    public ListadoUsuarios() {
        listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new Usuario("Jorge Ruanova","123", "32952245",false));
        listaUsuarios.add(new Usuario("Luis Perez","123", "87654321",false));
        listaUsuarios.add(new Usuario("Juanito Casas","123", "111",true,"Cardiologo"));
        listaUsuarios.add(new Usuario("Pedro Gomez","123", "222",true, "Nutricionista"));
    }


    public Usuario validarUsuario(String dni, String contrasenia) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getDniUsuario().equals(dni)) {
                // dni existe
                if (usuario.getContrasenia().equals(contrasenia)) {
                    // pass ok
                    return usuario;
                } else {
                   // pass error
                    return null;
                }
            }
        }
        // user no existe, no dar pistas
        return null;
    }
}
