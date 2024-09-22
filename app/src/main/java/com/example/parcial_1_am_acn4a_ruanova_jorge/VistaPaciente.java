package com.example.parcial_1_am_acn4a_ruanova_jorge;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;


public class VistaPaciente extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_paciente);

//        UsuarioLogueado.getInstance().setNombreUsuario("Jorge Ruanova");
//        UsuarioLogueado.getInstance().setDniUsuario("32952245");
//        TextView usuarioLogueadoTextView = findViewById(R.id.usuario_logueado);
//        usuarioLogueadoTextView.setText(UsuarioLogueado.getInstance().getNombreUsuario());


        Button btnNuevoTurno = findViewById(R.id.btn_nuevo_turno);
        btnNuevoTurno.setOnClickListener(view -> {
            Intent intentNuevoTurno = new Intent(VistaPaciente.this, NuevoTurno.class);
            ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(
                    VistaPaciente.this,
                    R.anim.slide_in_right,
                    R.anim.slide_out_left
            );
            startActivity(intentNuevoTurno, options.toBundle());
        });

        Button btnMisTurnos = findViewById(R.id.btn_mis_turnos);

        btnMisTurnos.setOnClickListener(View -> Toast.makeText(VistaPaciente.this, "Proximamente", Toast.LENGTH_LONG).show());

        Button btnMensajes = findViewById(R.id.btn_mensajes);
        btnMensajes.setOnClickListener(View -> Toast.makeText(VistaPaciente.this, "Proximamente", Toast.LENGTH_LONG).show());

        Button btnMisDatos = findViewById(R.id.btn_mis_datos);
        btnMisDatos.setOnClickListener(View -> Toast.makeText(VistaPaciente.this, "Proximamente", Toast.LENGTH_LONG).show());

        Button btnSalir = findViewById(R.id.btn_salir);
        btnSalir.setOnClickListener(View -> Toast.makeText(VistaPaciente.this, "Deslogueando...", Toast.LENGTH_LONG).show());


    }
}
