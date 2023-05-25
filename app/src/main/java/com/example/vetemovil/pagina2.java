package com.example.vetemovil;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vetemovil.interfaces.UsuarioAPI;
import com.example.vetemovil.models.Usuario;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class pagina2 extends AppCompatActivity {
    EditText nombreUsuario;
    EditText telefono;
    EditText correo;
    EditText contrasena;
    CheckBox terminos;
    Button registrar;
    String textNombreUsuario;
    String textTelefono;
    String textCorreo;
    String textContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina2);

        nombreUsuario = findViewById(R.id.editTextTextPersonName5);
        telefono = findViewById(R.id.editTextTextPersonName4);
        correo = findViewById(R.id.editTextTextPersonName7);
        contrasena = findViewById(R.id.editTextTextPersonName6);
        terminos = findViewById(R.id.checkBox);
        registrar = findViewById(R.id.button5);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(terminos.isChecked()){
                    textNombreUsuario = nombreUsuario.getText().toString();
                    textTelefono = telefono.getText().toString();
                    textCorreo = correo.getText().toString();
                    textContrasena = contrasena.getText().toString();

                    if(textNombreUsuario.isEmpty() || textTelefono.isEmpty() || textCorreo.isEmpty() || textContrasena.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Todos los campos son requeridos", Toast.LENGTH_SHORT).show();
                    } else {

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void enviarUsuario(int numero) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://vetemovil.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UsuarioAPI UsuarioAPI = retrofit.create(UsuarioAPI.class);

        Usuario Usuario = new Usuario();
        Usuario.setNombre("Jhon");
        Usuario.setTelefono("312");
        Usuario.setEmail("Luna");
        Usuario.setContrasena("Prueba1");

        Call<Usuario> call = UsuarioAPI.enviarUsuario(Usuario);

        // ...
    }

}