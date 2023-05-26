package com.example.vetemovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class pagina4 extends AppCompatActivity {
    ImageButton programarCitas;
    ImageButton veterinaria;

    ImageButton perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina4);

        programarCitas = findViewById(R.id.imageButton);
        veterinaria = findViewById(R.id.imageButton2);
        perfil = findViewById(R.id.imageButton3);

        programarCitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityCitas();
            }
        });

        veterinaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityVeterinaria();
            }
        });

        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityPerfil();
            }
        });
    }

    public void activityCitas(){
        Intent citas = new Intent(this, pagina5.class);
        startActivity(citas);
    }

    public void activityVeterinaria(){
        Intent veterinaria = new Intent(this, activity_pagina6.class);
        startActivity(veterinaria);
    }

    public void activityPerfil(){
        Intent perfil = new Intent(this, pagina_final.class);
        startActivity(perfil);
    }
}