package com.example.parcial_1_am_acn4a_ruanova_jorge;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ListadoUsuariosLog";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListadoUsuarios listado = new ListadoUsuarios();

        EditText inputDni = findViewById(R.id.input_dni);
        EditText inputPassword = findViewById(R.id.input_password);




        Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(view -> {
            String userDni = inputDni.getText().toString().trim();
            String userPass = inputPassword.getText().toString().trim();

            if (userDni.isEmpty() || userPass.isEmpty()) {
                Toast.makeText(MainActivity.this,
                        "Debe completar usuario y contraseña para ingresar", Toast.LENGTH_LONG).show();
            } else {
                Usuario resultadoLogin = listado.validarUsuario(userDni, userPass);

                if (resultadoLogin == null) {
                    Log.d(TAG, "Los datos ingresados no son válidos");
                    Toast.makeText(MainActivity.this,
                            "Los datos ingresados son inválidos", Toast.LENGTH_LONG).show();
                } else {
                    Log.d(TAG, resultadoLogin.getNombreUsuario() + " - " + resultadoLogin.getContrasenia()+resultadoLogin.isDoctor());
                    Toast.makeText(MainActivity.this,
                            "DNI: " + userDni + "\nPassword: " + userPass + resultadoLogin.isDoctor(), Toast.LENGTH_LONG).show();
                    manejarVista(resultadoLogin.isDoctor());
                }
            }
        });

        Button btnRecuperar = findViewById(R.id.btn_reset);
        btnRecuperar.setOnClickListener(View -> Toast.makeText(MainActivity.this, "Recuperando", Toast.LENGTH_LONG).show());
    }


    private void manejarVista(boolean esDoctor) {
        if (esDoctor) {
            lanzarVistaMedico();
        } else {
            lanzarVistaPaciente();
        }
    }

    private void lanzarVistaPaciente() {
        Intent intentVistaPaciente = new Intent(MainActivity.this, VistaPaciente.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(
                MainActivity.this,
                R.anim.slide_in_right,
                R.anim.slide_out_left
        );
        startActivity(intentVistaPaciente, options.toBundle());
        Log.d(TAG, "Es paciente");
    }

    private void lanzarVistaMedico() {
       Intent intentVistaDoctor = new Intent(MainActivity.this, VistaDoctor.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(
                MainActivity.this,
                R.anim.slide_in_right,
                R.anim.slide_out_left
        );
        startActivity(intentVistaDoctor, options.toBundle());

        Log.d(TAG, "Es doctor");
    }
}
