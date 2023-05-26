package com.example.vetemovil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class pagina5 extends AppCompatActivity {

    private Spinner spinner;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> options;
    Button menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina5); // Inflar el diseño correcto

        spinner = findViewById(R.id.spinner);
        listView = findViewById(R.id.listView);

        options = new ArrayList<>();
        options.add("Veterinaria DogCat");
        options.add("Veterinaria LoveDog");
        options.add("Veterinaria Chaus");
        options.add("Veterinaria Doctora Carla");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, options);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        menu = findViewById(R.id.inicio);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu();
            }
        });

        // Agregar un evento para obtener las selecciones
        //Button obtenerSeleccionesButton = findViewById(R.id.nombreveterinaria);
        /*obtenerSeleccionesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerSelecciones();
            }
        });*/
    }

    public void menu(){
        Intent menu = new Intent(this, pagina4.class);
        startActivity(menu);
        finish();
    }

    /*private void obtenerSelecciones() {
        List<String> selectedOptions = new ArrayList<>();
        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
        for (int i = 0; i < listView.getCount(); i++) {
            if (checkedItems.get(i)) {
                selectedOptions.add(options.get(i));
            }
        }

        // Hacer algo con las selecciones
        for (String option : selectedOptions) {
            Log.d("Selección", option);
        }
    }*/
}
