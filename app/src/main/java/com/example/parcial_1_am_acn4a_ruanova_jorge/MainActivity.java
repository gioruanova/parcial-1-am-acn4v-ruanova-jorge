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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instancio la clase de usuarios para tener algunos usuarios ya registrados
        ListadoUsuarios listado = new ListadoUsuarios();

        // Proceso de login y validaciones
        EditText inputEmail = findViewById(R.id.input_email);
        EditText inputPassword = findViewById(R.id.input_password);

        Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(view -> {
            String userEmail = inputEmail.getText().toString().trim();
            String userPass = inputPassword.getText().toString().trim();

//            if (userDni.isEmpty() || userPass.isEmpty()) {
//                Toast.makeText(MainActivity.this,
//                        getString(R.string.error_log_campos_vacios), Toast.LENGTH_LONG).show();
//            } else {
//                Usuario resultadoLogin = listado.validarUsuario(userDni, userPass);
//
//                if (resultadoLogin == null) {
//                    Toast.makeText(MainActivity.this,
//                            getString(R.string.error_log_datos_invalidos), Toast.LENGTH_LONG).show();
//                } else {
//                    manejarVista(resultadoLogin.isDoctor(),resultadoLogin);
//                }
//            }

            if (userEmail.isEmpty() || userPass.isEmpty()) {
                Toast.makeText(MainActivity.this,
                        getString(R.string.error_log_campos_vacios), Toast.LENGTH_LONG).show();
            } else {
                Usuario resultadoLogin = listado.validarPerfil(userEmail);

                if (resultadoLogin == null) {
                    Toast.makeText(MainActivity.this,
                            getString(R.string.error_log_datos_invalidos), Toast.LENGTH_LONG).show();
                } else {
                    manejarVista(resultadoLogin.isDoctor(), resultadoLogin);
                    Log.i("usuario", resultadoLogin.getNombreUsuario());
                }



            }
        });

        Button btnRecuperar = findViewById(R.id.btn_reset);
        btnRecuperar.setOnClickListener(View -> Toast.makeText(MainActivity.this, "Navegacion a vista recuperar contraseña", Toast.LENGTH_LONG).show());
    }


    // metodo auxiliar para definir si es doctor o paciente
    private void manejarVista(boolean esDoctor, Usuario usuario) {
        if (esDoctor) {
            lanzarVistaMedico(usuario);
        } else {
            lanzarVistaPaciente(usuario);
        }
    }

    // lanzamiento de vista paciente
    private void lanzarVistaPaciente(Usuario usuario) {
        Intent intentVistaPaciente = new Intent(MainActivity.this, VistaPaciente.class);
        intentVistaPaciente.putExtra("usuario", usuario);  // Envio el user que se loguea (pacientea)

        ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(
                MainActivity.this,
                R.anim.slide_in_right,
                R.anim.slide_out_left
        );
        startActivity(intentVistaPaciente, options.toBundle());
    }

    // lanzamiento de vista doctor
    private void lanzarVistaMedico(Usuario usuario) {
        Intent intentVistaDoctor = new Intent(MainActivity.this, VistaDoctor.class);
        intentVistaDoctor.putExtra("usuario", usuario);  // Envio el user que se loguea (dr)

        ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(
                MainActivity.this,
                R.anim.slide_in_right,
                R.anim.slide_out_left
        );
        startActivity(intentVistaDoctor, options.toBundle());
    }
}
