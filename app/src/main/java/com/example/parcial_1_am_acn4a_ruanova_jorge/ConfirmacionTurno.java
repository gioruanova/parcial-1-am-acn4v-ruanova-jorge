package com.example.parcial_1_am_acn4a_ruanova_jorge;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;


import java.util.ArrayList;

public class ConfirmacionTurno extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion_turno);


        // recupero el usuario logueado
        Usuario usuarioLogueado = (Usuario) getIntent().getSerializableExtra("usuario");
        TextView usuarioLogueadoTextView = findViewById(R.id.usuario_logueado);
        assert usuarioLogueado != null;
        usuarioLogueadoTextView.setText(usuarioLogueado.getNombreUsuario());


        // Confirmo el turno realizado
        TextView infoTurno = findViewById(R.id.infoTurno);
        ListadoTurnos registroTurnos = ListadoTurnos.obtenerInstancia();
        ArrayList<TurnoMedico> turnos = registroTurnos.obtenerTurnos();

        // Muestro el turno confirmado
        for (TurnoMedico turno : turnos) {
            if (turno.getUsuario().equals(usuarioLogueado)) {
                infoTurno.setText( getString(R.string.turno_confirmacion_texto,
                        turno.getUsuario().getNombreUsuario(),
                        turno.getEspecialidad(),
                        turno.getId(),
                        turno.getFechaTurno(),
                        turno.getHoraTurno()));
            }
        }


        Button volverHome = findViewById(R.id.btn_volver_home);
        volverHome.setOnClickListener(view -> {
            Intent volverAVistaPrincipal = new Intent(ConfirmacionTurno.this, VistaPaciente.class);
            volverAVistaPrincipal.putExtra("usuario", usuarioLogueado);
            ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(
                    ConfirmacionTurno.this,
                    R.anim.slide_in_left,
                    R.anim.slide_out_right
            );
            startActivity(volverAVistaPrincipal, options.toBundle());
        });

    }
}