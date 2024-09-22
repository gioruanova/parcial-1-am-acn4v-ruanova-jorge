package com.example.parcial_1_am_acn4a_ruanova_jorge;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class VistaDoctor extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_doctor);

//        UsuarioLogueado.getInstance().setNombreUsuario("Jorge Ruanova");
//        UsuarioLogueado.getInstance().setDniUsuario("32952245");
//        TextView usuarioLogueadoTextView = findViewById(R.id.usuario_logueado);
//        usuarioLogueadoTextView.setText(UsuarioLogueado.getInstance().getNombreUsuario());


        Button btnMisTurnosDoctor = findViewById(R.id.btn_turnos_doctor);
        btnMisTurnosDoctor.setOnClickListener(View -> Toast.makeText(VistaDoctor.this, "Mostrando vista turnos del medico...", Toast.LENGTH_LONG).show());

        Button btnMisDatosDoctor = findViewById(R.id.btn_mis_datos_doctor);
        btnMisDatosDoctor.setOnClickListener(View -> Toast.makeText(VistaDoctor.this, "Mostrando vista datos medicos", Toast.LENGTH_LONG).show());

        Button btnMensajesDoctor = findViewById(R.id.btn_mensajes_doctor);
        btnMensajesDoctor.setOnClickListener(View -> Toast.makeText(VistaDoctor.this, "Mostrando vista mensajes medicos", Toast.LENGTH_LONG).show());

        Button btnSalir = findViewById(R.id.btn_salir);
        btnSalir.setOnClickListener(View -> Toast.makeText(VistaDoctor.this, "Deslogueando...", Toast.LENGTH_LONG).show());

    }
}
