package com.example.entrega;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class UserLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userloginactivity);

        final EditText etNombre     = findViewById(R.id.etUserUsername);
        final EditText etContrasena = findViewById(R.id.etUserPassword);
        Button btnLogin             = findViewById(R.id.btnUserLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre     = etNombre.getText().toString().trim();
                String contrasena = etContrasena.getText().toString().trim();

                boolean encontrado = false;
                for (Usuario u : MainActivity.listaUsuarios) {
                    if (u.getNombre().equals(nombre) && u.getContrasena().equals(contrasena)) {
                        encontrado = true;
                        break;
                    }
                }

                if (encontrado) {
                    Toast.makeText(UserLoginActivity.this, "Bienvenido, " + nombre, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UserLoginActivity.this, UserWelcomeActivity.class);
                    intent.putExtra("username", nombre);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(UserLoginActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}