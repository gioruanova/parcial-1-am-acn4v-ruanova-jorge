package com.example.parcial_1_am_acn4a_ruanova_jorge;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ConfirmacionTurno extends AppCompatActivity {
    private VibrarBoton vibrarBoton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirmacion_turno);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        vibrarBoton = new VibrarBoton(this);
        Button volverHome = findViewById(R.id.btn_volver_home);

        volverHome.setOnClickListener(view -> {
            Intent intent = new Intent(ConfirmacionTurno.this, MainActivity.class);
            vibrarBoton.vibrate();
            startActivity(intent); // lanzo actividad home ->main

            // desplazamiento lateral
            ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(ConfirmacionTurno.this, R.anim.slide_in_left, R.anim.slide_out_right);
            startActivity(intent, options.toBundle());
        });

    }
}