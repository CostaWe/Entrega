package com.example.entrega;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;

public class AgendaActivity extends AppCompatActivity {

    EditText etId, nombre, apellido, telefono, direccion, etBuscarId;
    RadioGroup grupoGustos, grupoPreferencias;
    ListView listaContactos;
    ArrayAdapter<Contacto> adapter;
    ArrayList<Contacto> agenda = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        etId      = findViewById(R.id.etId);
        nombre    = findViewById(R.id.nombre);
        apellido  = findViewById(R.id.apellido);
        telefono  = findViewById(R.id.telefono);
        direccion = findViewById(R.id.direccion);
        etBuscarId = findViewById(R.id.etBuscarId);

        grupoGustos       = findViewById(R.id.grupoGustos);
        grupoPreferencias = findViewById(R.id.grupoPreferencias);
        listaContactos    = findViewById(R.id.listaContactos);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, agenda);
        listaContactos.setAdapter(adapter);

        for (String g : Contacto.GUSTOS) {
            RadioButton rb = new RadioButton(this);
            rb.setText(g);
            grupoGustos.addView(rb);
        }

        for (String p : Contacto.PREFERENCIAS) {
            RadioButton rb = new RadioButton(this);
            rb.setText(p);
            grupoPreferencias.addView(rb);
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

        // Verificar que la ID no esté duplicada
        for (Contacto c : agenda) {
            if (c.getId().equals(id)) {
                Toast.makeText(this, "Ya existe un contacto con esa ID", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        String gusto = "";
        int idGusto = grupoGustos.getCheckedRadioButtonId();
        if (idGusto != -1) {
            RadioButton rb = findViewById(idGusto);
            gusto = rb.getText().toString();
        }

        String preferencia = "";
        int idPref = grupoPreferencias.getCheckedRadioButtonId();
        if (idPref != -1) {
            RadioButton rb = findViewById(idPref);
            preferencia = rb.getText().toString();
        }

        Contacto contacto = new Contacto(id, n, a, t, d, gusto, preferencia);
        agenda.add(contacto);
        adapter.notifyDataSetChanged();

        Toast.makeText(this, "Contacto guardado", Toast.LENGTH_SHORT).show();

        etId.setText("");
        nombre.setText("");
        apellido.setText("");
        telefono.setText("");
        direccion.setText("");
        grupoGustos.clearCheck();
        grupoPreferencias.clearCheck();
    }

    public void buscarPorId(View v) {
        String texto = etBuscarId.getText().toString().trim();

        if (texto.isEmpty()) {
            Toast.makeText(this, "Ingresa una ID para buscar", Toast.LENGTH_SHORT).show();
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
                    .setTitle("Contacto ID: " + texto)
                    .setMessage(
                            "Nombre: " + encontrado.getNombre() + " " + encontrado.getApellido() +
                                    "\nTeléfono: " + encontrado.getTelefono() +
                                    "\nDirección: " + encontrado.getDireccion() +
                                    "\nGusto: " + encontrado.getGusto() +
                                    "\nPreferencia: " + encontrado.getPreferencia()
                    )
                    .setPositiveButton("Cerrar", null)
                    .show();
        } else {
            Toast.makeText(this, "No se encontró un contacto con esa ID", Toast.LENGTH_SHORT).show();
        }

        etBuscarId.setText("");
    }

    public void volver(View v) {
        finish();
    }
}