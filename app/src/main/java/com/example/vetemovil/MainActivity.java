package com.example.vetemovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button botonInicioSesion;
    Button registrarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonInicioSesion = findViewById(R.id.button);
        registrarUsuario = findViewById(R.id.button3);

        botonInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pruebas();
            }
        });

        registrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityRegistro();
            }
        });

    }

    public void activityRegistro(){
        Intent registro = new Intent(this, pagina2.class);
        startActivity(registro);
        finish();
    }

    public void pruebas(){
        Intent prueba = new Intent(this, MainActivity2.class);
        startActivity(prueba);
    }
}