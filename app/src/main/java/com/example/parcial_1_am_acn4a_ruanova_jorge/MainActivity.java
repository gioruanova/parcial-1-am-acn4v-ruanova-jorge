package com.example.parcial_1_am_acn4a_ruanova_jorge;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private VibrarBoton vibrarBoton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        vibrarBoton = new VibrarBoton(this);


        Button btnNuevoTurno = findViewById(R.id.btn_nuevo_turno);
        btnNuevoTurno.setOnClickListener(view -> vibrarBoton.vibrate(view));

        Button btnMisTurnos = findViewById(R.id.btn_mis_turnos);
        btnMisTurnos.setOnClickListener(view -> vibrarBoton.vibrate(view));

        Button btnMensajes = findViewById(R.id.btn_mensajes);
        btnMensajes.setOnClickListener(view -> vibrarBoton.vibrate(view));

        Button btnMisDatos = findViewById(R.id.btn_mis_datos);
        btnMisDatos.setOnClickListener(view -> vibrarBoton.vibrate(view));

        Button btnSalir = findViewById(R.id.btn_salir);
        btnSalir.setOnClickListener(view -> vibrarBoton.vibrate(view));
    }
}