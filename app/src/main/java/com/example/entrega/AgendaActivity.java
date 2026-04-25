package com.example.entrega;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;

public class AgendaActivity extends AppCompatActivity {

    EditText etId, nombre, apellido, telefono, direccion, etBuscarId;
    LinearLayout contenedorGustos, contenedorPreferencias;
    ListView listaContactos;
    ArrayAdapter<Contacto> adapter;
    ArrayList<Contacto> agenda = new ArrayList<>();
    ArrayList<CheckBox> checkboxGustos = new ArrayList<>();
    ArrayList<CheckBox> checkboxPreferencias = new ArrayList<>();
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        username               = getIntent().getStringExtra("username");
        etId                   = findViewById(R.id.etId);
        nombre                 = findViewById(R.id.nombre);
        apellido               = findViewById(R.id.apellido);
        telefono               = findViewById(R.id.telefono);
        direccion              = findViewById(R.id.direccion);
        etBuscarId             = findViewById(R.id.etBuscarId);
        contenedorGustos       = findViewById(R.id.contenedorGustos);
        contenedorPreferencias = findViewById(R.id.contenedorPreferencias);
        listaContactos         = findViewById(R.id.listaContactos);

        agenda = GestorDatos.cargarContactos(this, username);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, agenda);
        listaContactos.setAdapter(adapter);

        for (String g : Contacto.OPCIONES_GUSTOS) {
            CheckBox cb = new CheckBox(this);
            cb.setText(g);
            contenedorGustos.addView(cb);
            checkboxGustos.add(cb);
        }

        for (String p : Contacto.OPCIONES_PREFERENCIAS) {
            CheckBox cb = new CheckBox(this);
            cb.setText(p);
            contenedorPreferencias.addView(cb);
            checkboxPreferencias.add(cb);
        }
    }

    public void guardar(View v) {
        String id = etId.getText().toString().trim();
        String n  = nombre.getText().toString().trim();
        String a  = apellido.getText().toString().trim();
        String t  = telefono.getText().toString().trim();
        String d  = direccion.getText().toString().trim();

        if (id.isEmpty() || n.isEmpty() || a.isEmpty() || t.isEmpty() || d.isEmpty()) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        for (Contacto c : agenda) {
            if (c.getId().equals(id)) {
                Toast.makeText(this, "Ya existe un contacto con esa cédula", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        ArrayList<String> gustosSeleccionados = new ArrayList<>();
        for (CheckBox cb : checkboxGustos) {
            if (cb.isChecked()) {
                gustosSeleccionados.add(cb.getText().toString());
            }
        }

        ArrayList<String> preferenciasSeleccionadas = new ArrayList<>();
        for (CheckBox cb : checkboxPreferencias) {
            if (cb.isChecked()) {
                preferenciasSeleccionadas.add(cb.getText().toString());
            }
        }

        Contacto contacto = new Contacto(id, n, a, t, d, gustosSeleccionados, preferenciasSeleccionadas);
        agenda.add(contacto);
        adapter.notifyDataSetChanged();
        GestorDatos.guardarContactos(this, agenda, username);

        Toast.makeText(this, "Contacto guardado", Toast.LENGTH_SHORT).show();

        etId.setText("");
        nombre.setText("");
        apellido.setText("");
        telefono.setText("");
        direccion.setText("");
        for (CheckBox cb : checkboxGustos) cb.setChecked(false);
        for (CheckBox cb : checkboxPreferencias) cb.setChecked(false);
    }

    public void buscarPorId(View v) {
        String texto = etBuscarId.getText().toString().trim();

        if (texto.isEmpty()) {
            Toast.makeText(this, "Ingresa una cédula para buscar", Toast.LENGTH_SHORT).show();
            return;
        }

        Contacto encontrado = null;
        for (Contacto c : agenda) {
            if (c.getId().equals(texto)) {
                encontrado = c;
                break;
            }
        }

        if (encontrado != null) {
            new AlertDialog.Builder(this)
                    .setTitle("Contacto: " + texto)
                    .setMessage(
                            "Nombre: " + encontrado.getNombre() + " " + encontrado.getApellido() +
                                    "\nTeléfono: " + encontrado.getTelefono() +
                                    "\nDirección: " + encontrado.getDireccion() +
                                    "\nGustos: " + encontrado.getGustos().toString() +
                                    "\nPreferencias: " + encontrado.getPreferencias().toString()
                    )
                    .setPositiveButton("Cerrar", null)
                    .show();
        } else {
            Toast.makeText(this, "No se encontró un contacto con esa cédula", Toast.LENGTH_SHORT).show();
        }

        etBuscarId.setText("");
    }

    public void irCalculadora(View v) {
        startActivity(new Intent(this, CalculadoraActivity.class));
    }

    public void volver(View v) {
        finish();
    }
}