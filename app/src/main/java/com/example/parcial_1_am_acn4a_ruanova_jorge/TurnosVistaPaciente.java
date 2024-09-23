package com.example.parcial_1_am_acn4a_ruanova_jorge;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import java.util.ArrayList;


public class TurnosVistaPaciente extends AppCompatActivity {

    private static final String TAG = "listaturnos";
    private ListView listadoTurnosPaciente;


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
        listadoTurnosPaciente = findViewById(R.id.listado_turnos_paciente);

        ArrayList<String> turnosStrings = new ArrayList<>();
        for (TurnoMedico turno : turnos) {
            String turnoString = "Dr. " + turno.getUsuario().getNombreUsuario() + "\n" +
                    "Especialidad: " + turno.getEspecialidad() + "\n" +
                    "Fecha: " + turno.getFechaTurno()+
                    "Hora: " + turno.getHoraTurno();
            turnosStrings.add(turnoString);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                turnosStrings
        );
        listadoTurnosPaciente.setAdapter(adapter);


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
