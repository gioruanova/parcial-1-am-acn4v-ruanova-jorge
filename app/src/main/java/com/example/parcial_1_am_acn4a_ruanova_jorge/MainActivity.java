package com.example.parcial_1_am_acn4a_ruanova_jorge;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        UsuarioLogueado.getInstance().setNombreUsuario("Jorge Ruanova");
        UsuarioLogueado.getInstance().setDniUsuario("32952245");
        TextView usuarioLogueadoTextView = findViewById(R.id.usuario_logueado);
        usuarioLogueadoTextView.setText(UsuarioLogueado.getInstance().getNombreUsuario());


        Button btnNuevoTurno = findViewById(R.id.btn_nuevo_turno);
        btnNuevoTurno.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NuevoTurno.class);
            ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(
                    MainActivity.this,
                    R.anim.slide_in_right,
                    R.anim.slide_out_left
            );
            startActivity(intent, options.toBundle());
        });

        Button btnMisTurnos = findViewById(R.id.btn_mis_turnos);

        btnMisTurnos.setOnClickListener(View -> Toast.makeText(MainActivity.this, "Proximamente", Toast.LENGTH_LONG).show());

        Button btnMensajes = findViewById(R.id.btn_mensajes);
        btnMensajes.setOnClickListener(View -> Toast.makeText(MainActivity.this, "Proximamente", Toast.LENGTH_LONG).show());

        Button btnMisDatos = findViewById(R.id.btn_mis_datos);
        btnMisDatos.setOnClickListener(View -> Toast.makeText(MainActivity.this, "Proximamente", Toast.LENGTH_LONG).show());

        Button btnSalir = findViewById(R.id.btn_salir);
        btnSalir.setOnClickListener(View -> Toast.makeText(MainActivity.this, "Proximamente", Toast.LENGTH_LONG).show());


    }
}
