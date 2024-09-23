package com.example.parcial_1_am_acn4a_ruanova_jorge;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;


public class VistaPaciente extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_paciente);

        Usuario usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        TextView usuarioLogueadoTextView = findViewById(R.id.usuario_logueado);
        assert usuario != null;
        usuarioLogueadoTextView.setText(usuario.getNombreUsuario());


        Button btnNuevoTurno = findViewById(R.id.btn_nuevo_turno);
        btnNuevoTurno.setOnClickListener(view -> {
            Intent intentNuevoTurno = new Intent(VistaPaciente.this, NuevoTurno.class);
            intentNuevoTurno.putExtra("usuario", usuario);  // envio el objeto usuarioo

            ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(
                    VistaPaciente.this,
                    R.anim.slide_in_right,
                    R.anim.slide_out_left
            );
            startActivity(intentNuevoTurno, options.toBundle());
        });

        Button btnMisTurnos = findViewById(R.id.btn_mis_turnos);
        btnMisTurnos.setOnClickListener(view -> {
            Intent intentMisTurnosPaciente = new Intent(VistaPaciente.this, TurnosVistaPaciente.class);
            intentMisTurnosPaciente.putExtra("usuario", usuario);  // envio el objeto usuarioo

            ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(
                    VistaPaciente.this,
                    R.anim.slide_in_right,
                    R.anim.slide_out_left
            );
            startActivity(intentMisTurnosPaciente, options.toBundle());
        });

        Button btnMensajes = findViewById(R.id.btn_mensajes);
        btnMensajes.setOnClickListener(View -> Toast.makeText(VistaPaciente.this, "Proximamente", Toast.LENGTH_LONG).show());

        Button btnMisDatos = findViewById(R.id.btn_mis_datos);
        btnMisDatos.setOnClickListener(View -> Toast.makeText(VistaPaciente.this, "Proximamente", Toast.LENGTH_LONG).show());

        Button btnSalir = findViewById(R.id.btn_volver_home);
        btnSalir.setOnClickListener(view -> Navegacion.desloguearUsuario(VistaPaciente.this, MainActivity.class));

    }
}
