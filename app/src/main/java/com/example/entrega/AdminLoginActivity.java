package com.example.entrega;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AdminLoginActivity extends AppCompatActivity {

    private static final String ADMIN_USER = "admin";
    private static final String ADMIN_PASS = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        final EditText etUsername = findViewById(R.id.etAdminUsername);
        final EditText etPassword = findViewById(R.id.etAdminPassword);
        Button btnLogin           = findViewById(R.id.btnAdminLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUsername.getText().toString().trim();
                String pass = etPassword.getText().toString().trim();

                if (user.equals(ADMIN_USER) && pass.equals(ADMIN_PASS)) {
                    Toast.makeText(AdminLoginActivity.this, "Bienvenido, Admin", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdminLoginActivity.this, AdminPanelActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(AdminLoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}