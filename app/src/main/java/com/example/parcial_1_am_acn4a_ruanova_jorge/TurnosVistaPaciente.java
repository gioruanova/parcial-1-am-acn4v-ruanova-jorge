package com.example.parcial_1_am_acn4a_ruanova_jorge;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
        ArrayList<String> turnosList = new ArrayList<>();

        ListView miListaTurnos = findViewById(R.id.listado_turnos_lista);


        ViewGroup parent = (ViewGroup) miListaTurnos.getParent();
        TextView textoError = findViewById(R.id.texto_error);


        if (turnos.size() > 0) {
            for (TurnoMedico turno : turnos) {
                if(turno.getUsuario().getDniUsuario().equals(usuarioLogueado.getDniUsuario())){
                    Log.d(TAG, "Turno: " + turno.getFechaTurno()+" - "+turno.getHoraTurno() + " - " + turno.getEspecialidad());
                    String texto = "Turno: " + turno.getFechaTurno()+" - "+turno.getHoraTurno() + " - " + turno.getEspecialidad();
                    turnosList.add(texto);
                    ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this, R.layout.item_turno_paciente, turnosList);


                    miListaTurnos.setAdapter(myAdapter);
                    parent.removeView(textoError);


                }else{
                    Log.d(TAG, "No hay turnos para el usuario");

                }
            }
        }else{
            Log.d(TAG, "No hay turnos");
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
