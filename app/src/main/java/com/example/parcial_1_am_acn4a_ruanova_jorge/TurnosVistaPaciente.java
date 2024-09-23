package com.example.parcial_1_am_acn4a_ruanova_jorge;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import java.util.ArrayList;


public class TurnosVistaPaciente extends AppCompatActivity {

    private static final String TAG = "listaturnos";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.turnos_vista_paciente);

        Usuario usuarioLogueado = (Usuario) getIntent().getSerializableExtra("usuario");
        TextView usuarioLogueadoTextView = findViewById(R.id.usuario_logueado);
        assert usuarioLogueado != null;
        usuarioLogueadoTextView.setText(usuarioLogueado.getNombreUsuario());

        ListadoTurnos listaDeTurnos = ListadoTurnos.obtenerInstancia();
        ArrayList<TurnoMedico> turnos = listaDeTurnos.obtenerTurnos();


        LinearLayout listadoTurnos = findViewById(R.id.lista_turnos_inner);


        for (TurnoMedico turno : turnos) {
            TextView registroTurno = new TextView(this);
            registroTurno.setTextColor(getResources().getColor(android.R.color.black, getTheme()));
            registroTurno.setText(turno.getId() + " - " + turno.getFechaTurno() + " - " + turno.getHoraTurno() + " - "  + turno.getEspecialidad());
            listadoTurnos.addView(registroTurno);
            Log.d(TAG, "Turno: " + turno.getUsuario().getNombreUsuario() + ", Especialidad: " + turno.getEspecialidad());
        }


        Button btnVolverHome = findViewById(R.id.btn_volver_home);

        btnVolverHome.setOnClickListener(view -> {
            Intent volverAVistaPrincipal = new Intent(TurnosVistaPaciente.this, VistaPaciente.class);
            volverAVistaPrincipal.putExtra("usuario", usuarioLogueado);
            ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(
                    TurnosVistaPaciente.this,
                    R.anim.slide_in_left,
                    R.anim.slide_out_right
            );
            startActivity(volverAVistaPrincipal, options.toBundle());});
    }
}
