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
import com.google.firebase.auth.FirebaseAuth;


public class ResetPassword extends AppCompatActivity {
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_reset);
        mAuth = FirebaseAuth.getInstance();


        EditText inputEmail = findViewById(R.id.input_email);


        Button btnReset = findViewById(R.id.btn_reset);
        btnReset.setOnClickListener(view -> {
            String userEmail = inputEmail.getText().toString().trim();
            if (userEmail.isEmpty()) {
                Toast.makeText(ResetPassword.this, R.string.error_recuperar1, Toast.LENGTH_LONG).show();
            } else {
                resetPassword(userEmail);
            }
        });
        Button btnSalir = findViewById(R.id.btn_volver);
        btnSalir.setOnClickListener(view -> Navegacion.desloguearUsuario(ResetPassword.this, MainActivity.class));
    }

    private void resetPassword(String email) {

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), ConfirmacionResetPassword.class);
                            ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(
                                    getApplicationContext(),
                                    R.anim.slide_in_right,
                                    R.anim.slide_out_left
                            );
                            startActivity(intent, options.toBundle());
                        } else {
                            Toast.makeText(ResetPassword.this, R.string.error_recuperar2, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}