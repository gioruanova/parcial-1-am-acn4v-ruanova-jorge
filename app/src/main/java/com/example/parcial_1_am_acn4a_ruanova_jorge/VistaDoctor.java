package com.example.parcial_1_am_acn4a_ruanova_jorge;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class VistaDoctor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_doctor);

        Usuario usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        TextView usuarioLogueadoTextView = findViewById(R.id.usuario_logueado);
        assert usuario != null;
        String usuarioSaludo = getString(R.string.medico_saludo, usuario.getNombreUsuario());
        usuarioLogueadoTextView.setText(usuarioSaludo);


        Button btnMisTurnosDoctor = findViewById(R.id.btn_turnos_doctor);
        btnMisTurnosDoctor.setOnClickListener(View -> Toast.makeText(VistaDoctor.this, "Navegando a vista turnos del medico...", Toast.LENGTH_LONG).show());

        Button btnMisDatosDoctor = findViewById(R.id.btn_mis_datos_doctor);
        btnMisDatosDoctor.setOnClickListener(View -> Toast.makeText(VistaDoctor.this, "Navegando a vista datos medicos", Toast.LENGTH_LONG).show());

        Button btnMensajesDoctor = findViewById(R.id.btn_mensajes_doctor);
        btnMensajesDoctor.setOnClickListener(View -> Toast.makeText(VistaDoctor.this, "Navegando a vista mensajes medicos", Toast.LENGTH_LONG).show());

        Button btnSalir = findViewById(R.id.btn_volver_home);
        btnSalir.setOnClickListener(view -> Navegacion.desloguearUsuario(VistaDoctor.this, MainActivity.class));

    }
}
