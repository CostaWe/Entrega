package com.example.entrega;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AdminPanelActivity extends AppCompatActivity {

    private TextView tvUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpanelactivity);

        final EditText etNombre     = findViewById(R.id.etNewUsername);
        final EditText etContrasena = findViewById(R.id.etNewPassword);
        Button btnRegistrar         = findViewById(R.id.btnRegisterUser);
        tvUserList                  = findViewById(R.id.tvUserList);

        mostrarUsuarios();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre     = etNombre.getText().toString().trim();
                String contrasena = etContrasena.getText().toString().trim();

                if (nombre.isEmpty() || contrasena.isEmpty()) {
                    Toast.makeText(AdminPanelActivity.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean yaExiste = false;
                for (Usuario u : MainActivity.listaUsuarios) {
                    if (u.getNombre().equals(nombre)) {
                        yaExiste = true;
                        break;
                    }
                }

                if (yaExiste) {
                    Toast.makeText(AdminPanelActivity.this, "Ese usuario ya existe", Toast.LENGTH_SHORT).show();
                } else {
                    Usuario nuevoUsuario = new Usuario(nombre, contrasena);
                    MainActivity.listaUsuarios.add(nuevoUsuario);
                    Toast.makeText(AdminPanelActivity.this, "Usuario registrado: " + nombre, Toast.LENGTH_SHORT).show();
                    etNombre.setText("");
                    etContrasena.setText("");
                    mostrarUsuarios();
                }
            }
        });
    }

    private void mostrarUsuarios() {
        if (MainActivity.listaUsuarios.isEmpty()) {
            tvUserList.setText("Aún no hay usuarios registrados.");
        } else {
            StringBuilder sb = new StringBuilder("Usuarios registrados:\n\n");
            for (Usuario u : MainActivity.listaUsuarios) {
                sb.append("• ").append(u.getNombre()).append("\n");
            }
            tvUserList.setText(sb.toString());
        }
    }
}