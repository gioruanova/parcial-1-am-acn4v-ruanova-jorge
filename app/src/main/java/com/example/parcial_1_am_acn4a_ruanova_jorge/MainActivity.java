package com.example.parcial_1_am_acn4a_ruanova_jorge;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private ListadoUsuarios listado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        listado = new ListadoUsuarios();
        db = FirebaseFirestore.getInstance();
        getUsersFirebase();


        // Deslogueo default en login inicial
        if (currentUser != null) {
            mAuth.signOut();
        }


        // Proceso de login y validaciones
        EditText inputEmail = findViewById(R.id.input_email);
        EditText inputPassword = findViewById(R.id.input_password);

        Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(view -> {
                    String userEmail = inputEmail.getText().toString().trim();
                    String userPass = inputPassword.getText().toString().trim();

                    if (userEmail.isEmpty() || userPass.isEmpty()) {
                        Toast.makeText(MainActivity.this,
                                getString(R.string.error_log_campos_vacios), Toast.LENGTH_LONG).show();
                    } else {

                        // Esta logica la voy a poder cambiar cuando firebase este listo
                        Usuario resultadoLogin = listado.validarPerfil(userEmail);

                        if (resultadoLogin == null) {
                            Toast.makeText(MainActivity.this,
                                    getString(R.string.error_log_datos_invalidos), Toast.LENGTH_LONG).show();
                        } else {

                            mAuth.signInWithEmailAndPassword(userEmail, userPass)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                manejarVista(resultadoLogin.isDoctor(), resultadoLogin);

                                            } else {
                                                Toast.makeText(MainActivity.this,
                                                        getString(R.string.error_log_datos_invalidos), Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        }
                    }
                }
        );

        Button btnRecuperar = findViewById(R.id.btn_reset);
//        btnRecuperar.setOnClickListener(View -> Toast.makeText(MainActivity.this, "Navegacion a vista recuperar contraseña", Toast.LENGTH_LONG).show());

        btnRecuperar.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ResetPassword.class);
            ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(
                    MainActivity.this,
                    R.anim.slide_in_right,
                    R.anim.slide_out_left
            );
            startActivity(intent, options.toBundle());
        });
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

    private void getUsersFirebase() {
        db.collection("usuarios")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String userName = document.getData().get("name").toString();
                                String userDni = document.getData().get("dni").toString();
                                String userEmail = document.getData().get("email").toString();
                                boolean isDoctor = (boolean) document.getData().get("isDoctor");

                                Usuario user;
                                if (isDoctor) {
                                    String userEspecialidad = document.getData().get("especialidad").toString();
                                    user = new Usuario(userName, userDni, isDoctor, userEspecialidad, userEmail);
                                } else {
                                    user = new Usuario(userName, userDni, isDoctor, userEmail);
                                }
                                listado.agregarUsuario(user);

                            }

                            // Mostrar usuarios solo para validar. Borrar antes de entregar
                            for (Usuario usuario : listado.getUsuarios()) {
                                Log.d("ListadoUsuarios", "Usuario: " + usuario.getDniUsuario() + ", Email: " + usuario.getEmailUsuario() + (usuario.isDoctor() ? ", Especialidad: " + usuario.getEspecialidad() : ""));
                            }
                            // Mostrar usuarios solo para validar. Borrar antes de entregar


                        } else {
                            Log.w("firebase-data", "Ha habido un error. Intente mas tarde", task.getException());
                        }
                    }
                });
    }
}
