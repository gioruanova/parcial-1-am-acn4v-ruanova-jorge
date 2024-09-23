package com.example.parcial_1_am_acn4a_ruanova_jorge;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class NuevoTurno extends AppCompatActivity {

    private static final String TAG = "listaturnos";


     EditText selectorFecha;
     Spinner spinnerEspecialidad;
     Date fechaSeleccionada;
     Spinner spinnerHorario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_turno);


        Usuario usuarioLogueado = (Usuario) getIntent().getSerializableExtra("usuario");
        TextView usuarioLogueadoTextView = findViewById(R.id.usuario_logueado);
        assert usuarioLogueado != null;
        usuarioLogueadoTextView.setText(usuarioLogueado.getNombreUsuario());


        ListadoTurnos listaDeTurnos = ListadoTurnos.obtenerInstancia();
        ArrayList<TurnoMedico> turnos = listaDeTurnos.obtenerTurnos();



        for (TurnoMedico turno : turnos) {
            Log.d(TAG, "Turno: " + turno.getFechaTurno() + " - " + turno.getHoraTurno() + " - " + turno.getEspecialidad());
        }




        // Gestion de turno y obtencion de datos
        // Seleccion de especialidad

        spinnerEspecialidad = findViewById(R.id.spinnerEspecialidad);

        ArrayAdapter<CharSequence> adapterEspecialidad = ArrayAdapter.createFromResource(this, R.array.listado_especialidades, android.R.layout.simple_spinner_item);
        adapterEspecialidad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEspecialidad.setAdapter(adapterEspecialidad);

        ArrayAdapter adapterSpinner = ArrayAdapter.createFromResource(
                this,
                R.array.listado_especialidades,
                R.layout.spinner_color_layout
        );
        adapterSpinner.setDropDownViewResource(R.layout.spinner_drop_down);
        spinnerEspecialidad.setAdapter(adapterSpinner);



        // Seleccion de fecha de turno
        selectorFecha = findViewById(R.id.seleccionFecha);
        selectorFecha.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int anio = calendar.get(Calendar.YEAR);
            int mes = calendar.get(Calendar.MONTH);
            int dia = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(NuevoTurno.this, (view, yearSelected, monthSelected, dayOfMonthSelected) -> {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(yearSelected, monthSelected, dayOfMonthSelected);
                fechaSeleccionada = selectedDate.getTime();
                String formattedDate = dayOfMonthSelected + "-" + (monthSelected + 1) + "-" + yearSelected;
                selectorFecha.setText(formattedDate);
            }, anio, mes, dia);

            datePickerDialog.show();
        });

        // Seleccion de hora de turno
        spinnerHorario = findViewById(R.id.spinnerHorario);

        ArrayAdapter<CharSequence> adapterHorario = ArrayAdapter.createFromResource(this, R.array.horarios_atencion, android.R.layout.simple_spinner_item);
        adapterHorario.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHorario.setAdapter(adapterHorario);



        ArrayAdapter adapterSpinnerHorario = ArrayAdapter.createFromResource(
                this,
                R.array.horarios_atencion,
                R.layout.spinner_color_layout
        );
        adapterSpinnerHorario.setDropDownViewResource(R.layout.spinner_drop_down);
        spinnerHorario.setAdapter(adapterSpinnerHorario);



        // Fin gestion de turno y obtencion de datos


        Button btnSolicitarTurno = findViewById(R.id.btn_solicitar_turno);
        btnSolicitarTurno.setOnClickListener(view -> {
            String especialidad = spinnerEspecialidad.getSelectedItem().toString();
            String hora = spinnerHorario.getSelectedItem().toString();





            // Condicion para completar los campos
            if (especialidad.isEmpty()) {
                Toast.makeText(NuevoTurno.this, "Debe seleccionar una especialidad", Toast.LENGTH_LONG).show();
                return;
            } else if (fechaSeleccionada == null) {
                Toast.makeText(NuevoTurno.this, "Debe seleccionar una fecha", Toast.LENGTH_LONG).show();
                return;
            } else if (fechaSeleccionada.before(Calendar.getInstance().getTime())) {
                Toast.makeText(NuevoTurno.this, "La fecha seleccionada no puede ser anterior a la fecha actual", Toast.LENGTH_LONG).show();
                return;
            } else if (hora.isEmpty()) {
                Toast.makeText(NuevoTurno.this, "Debe seleccionar una hora", Toast.LENGTH_LONG).show();
                return;
            }

            for (TurnoMedico turno : turnos) {
                if (especialidad.equals(turno.getEspecialidad()) && hora.equals(turno.getHoraTurno()) && normalizeDate(turno.getFechaTurno()).equals(normalizeDate(fechaSeleccionada))) {
                    Toast.makeText(NuevoTurno.this, "Turno ya ocupado", Toast.LENGTH_LONG).show();
                    return;
                }
            }





            TurnoMedico nuevoTurno = new TurnoMedico(usuarioLogueado, especialidad, fechaSeleccionada, hora);
//            ListadoTurnos listaDeTurnos = ListadoTurnos.obtenerInstancia();
            listaDeTurnos.agregarTurno(nuevoTurno);

            Intent intentConfirmacion = new Intent(NuevoTurno.this, ConfirmacionTurno.class);
            intentConfirmacion.putExtra("usuario", usuarioLogueado);
            startActivity(intentConfirmacion);

            // desplazamiento lateral
            ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(NuevoTurno.this, R.anim.slide_in_right,
                    R.anim.slide_out_left);
            startActivity(intentConfirmacion, options.toBundle());


        });

        Button btnVolverHome = findViewById(R.id.btn_volver_home);

        btnVolverHome.setOnClickListener(view -> {
            Intent volverAVistaPrincipal = new Intent(NuevoTurno.this, VistaPaciente.class);
            volverAVistaPrincipal.putExtra("usuario", usuarioLogueado);
            ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(
                    NuevoTurno.this,
                    R.anim.slide_in_left,
                    R.anim.slide_out_right
            );
            startActivity(volverAVistaPrincipal, options.toBundle());});

    }

    public static Date normalizeDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}