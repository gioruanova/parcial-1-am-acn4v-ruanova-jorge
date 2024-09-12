package com.example.parcial_1_am_acn4a_ruanova_jorge;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import java.util.Calendar;

public class NuevoTurno extends AppCompatActivity {

    private VibrarBoton vibrarBoton;
    private EditText selectorFecha;

    private Spinner spinnerEspecialidad;
    private String fechaSeleccionada;
    private Spinner spinnerHorario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nuevo_turno);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String nombreUsuario = UsuarioLogueado.getInstance().getNombreUsuario();
        TextView usuarioLogueadoTextView = findViewById(R.id.usuario_logueado);

        usuarioLogueadoTextView.setText(nombreUsuario);

        vibrarBoton = new VibrarBoton(this);
        Button btnNuevoTurno = findViewById(R.id.btn_solicitar_turno);
        Button btnVolverHome = findViewById(R.id.btn_volver_home);

        btnVolverHome.setOnClickListener(view -> {
            Intent intent = new Intent(NuevoTurno.this, MainActivity.class);
            vibrarBoton.vibrate();
            startActivity(intent); // lanzo actividad home ->main

            // desplazamiento lateral
            ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(NuevoTurno.this, R.anim.slide_in_left, R.anim.slide_out_right);
            startActivity(intent, options.toBundle());
        });


        // Seleccion de especialidad
        spinnerEspecialidad = findViewById(R.id.spinnerEspecialidad);
        ArrayAdapter<CharSequence> adapterEspecialidad = ArrayAdapter.createFromResource(this, R.array.listado_especialidades, android.R.layout.simple_spinner_item);
        adapterEspecialidad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEspecialidad.setAdapter(adapterEspecialidad);


        // Seleccion de fecha de turno
        selectorFecha = findViewById(R.id.seleccionFecha);
        selectorFecha.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int anio = calendar.get(Calendar.YEAR);
            int mes = calendar.get(Calendar.MONTH);
            int dia = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(NuevoTurno.this, (view, yearSelected, monthSelected, dayOfMonthSelected) -> {
                fechaSeleccionada = dayOfMonthSelected + "-" + (monthSelected + 1) + "-" + yearSelected;
                selectorFecha.setText(fechaSeleccionada);
            }, anio, mes, dia);

            datePickerDialog.show();
        });

        // Seleccion de hora de turno
        spinnerHorario = findViewById(R.id.spinnerHorario);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.horarios_atencion, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHorario.setAdapter(adapter);


        btnNuevoTurno.setOnClickListener(view -> {
            vibrarBoton.vibrate();
            String especialidad = spinnerEspecialidad.getSelectedItem().toString();
            String fecha = fechaSeleccionada;
            String hora = spinnerHorario.getSelectedItem().toString();


            // Condicion para completar los campos
            if (especialidad.isEmpty()) {
                Toast.makeText(NuevoTurno.this, "Debe seleccionar una especialidad", Toast.LENGTH_LONG).show();
                return;
            } else if (fecha == null) {
                Toast.makeText(NuevoTurno.this, "Debe seleccionar una fecha", Toast.LENGTH_LONG).show();
                return;
            } else if (hora.isEmpty()) {
                Toast.makeText(NuevoTurno.this, "Debe seleccionar una hora", Toast.LENGTH_LONG).show();
                return;
            }



            TurnoMedico turno = new TurnoMedico(nombreUsuario, especialidad, fecha, hora);
            RegistroTurnos listaDeTurnos = RegistroTurnos.obtenerInstancia();
            listaDeTurnos.agregarTurno(turno);

            Intent intent = new Intent(NuevoTurno.this, ConfirmacionTurno.class);
            vibrarBoton.vibrate();
            startActivity(intent); // lanzo actividad home ->main

            // desplazamiento lateral
            ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(NuevoTurno.this, R.anim.slide_in_right,
                    R.anim.slide_out_left);
            startActivity(intent, options.toBundle());


        });
    }
}