package com.example.entrega;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAdmin   = findViewById(R.id.btnAdmin);
        Button btnUsuario = findViewById(R.id.btnUsuario);

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AdminLoginActivity.class);
                startActivity(intent);
            }
        });

        btnUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}