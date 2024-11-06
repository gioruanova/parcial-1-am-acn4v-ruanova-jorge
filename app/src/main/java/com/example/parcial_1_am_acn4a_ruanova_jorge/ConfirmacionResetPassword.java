package com.example.parcial_1_am_acn4a_ruanova_jorge;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmacionResetPassword extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_reset_confirmado);
        Button btnSalir = findViewById(R.id.btn_volver);
        btnSalir.setOnClickListener(view -> Navegacion.desloguearUsuario(ConfirmacionResetPassword.this, MainActivity.class));
    }
}