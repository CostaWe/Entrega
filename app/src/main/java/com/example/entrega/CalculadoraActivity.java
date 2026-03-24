package com.example.entrega;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CalculadoraActivity extends AppCompatActivity {

    TextView caja;
    String operador = "";
    double num1 = 0;
    double memoria = 0;        // Resultado guardado en memoria
    boolean nuevoNumero = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);
        caja = findViewById(R.id.caja);
    }

    public void numero(View v) {
        Button b = (Button) v;
        if (nuevoNumero) {
            caja.setText(b.getText().toString());
            nuevoNumero = false;
        } else {
            caja.append(b.getText().toString());
        }
    }

    public void operador(View v) {
        Button b = (Button) v;
        operador = b.getText().toString();
        num1 = Double.parseDouble(caja.getText().toString());
        nuevoNumero = true;
    }

    public void igual(View v) {
        double num2 = Double.parseDouble(caja.getText().toString());
        double resultado = 0;

        switch (operador) {
            case "+": resultado = num1 + num2; break;
            case "-": resultado = num1 - num2; break;
            case "×": resultado = num1 * num2; break;
            case "÷":
                if (num2 == 0) {
                    Toast.makeText(this, "No se puede dividir entre 0", Toast.LENGTH_SHORT).show();
                    return;
                }
                resultado = num1 / num2;
                break;
        }

        caja.setText(String.valueOf(resultado));
        nuevoNumero = true;
    }

    public void limpiar(View v) {
        caja.setText("0");
        operador = "";
        num1 = 0;
        nuevoNumero = true;
    }

    public void punto(View v) {
        if (!caja.getText().toString().contains(".")) {
            caja.append(".");
        }
    }

    // Guarda el resultado actual en memoria y muestra Toast
    public void guardarMemoria(View v) {
        String texto = caja.getText().toString();
        if (!texto.isEmpty() && !texto.equals("0")) {
            memoria = Double.parseDouble(texto);
            Toast.makeText(this, "Guardado en memoria: " + memoria, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Nada que guardar", Toast.LENGTH_SHORT).show();
        }
    }

    public void volver(View v) {
        finish();
    }
}