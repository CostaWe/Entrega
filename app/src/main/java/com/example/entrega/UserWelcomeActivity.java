package com.example.entrega;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class UserWelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_welcome);

        String username    = getIntent().getStringExtra("username");
        TextView tvWelcome = findViewById(R.id.tvWelcome);
        tvWelcome.setText("¡Hola, " + username + "!");

        Button btnAgenda      = findViewById(R.id.btnAgenda);
        Button btnCalculadora = findViewById(R.id.btnCalculadora);
        Button btnLogout      = findViewById(R.id.btnLogout);

        btnAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserWelcomeActivity.this, AgendaActivity.class);
                startActivity(intent);
            }
        });

        btnCalculadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserWelcomeActivity.this, CalculadoraActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}