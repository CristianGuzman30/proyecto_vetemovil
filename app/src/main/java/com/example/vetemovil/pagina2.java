package com.example.vetemovil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vetemovil.interfaces.UsuarioAPI;
import com.example.vetemovil.models.Usuario;

import org.mindrot.jbcrypt.BCrypt;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
                        enviarUsuario();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void enviarUsuario() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://vetemovil.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UsuarioAPI usuarioAPI = retrofit.create(UsuarioAPI.class);
        String contraseñaEncriptada = generarHash(textContrasena);
        Usuario usuario = new Usuario();
        usuario.setNombre(textNombreUsuario);
        usuario.setTelefono(textTelefono);
        usuario.setEmail(textCorreo);
        usuario.setContrasena(contraseñaEncriptada);

        Call<Usuario> call = usuarioAPI.enviarUsuario(usuario);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                    regresarInicioSesion();
                } else {
                    Toast.makeText(getApplicationContext(), "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error en la comunicación con la API", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private String generarHash(String contraseña) {
        String salt = BCrypt.gensalt();
        String hash = BCrypt.hashpw(contraseña, salt);
        return hash;
    }

    public void regresarInicioSesion(){
        Intent inicioSesion = new Intent(this, MainActivity.class);
        startActivity(inicioSesion);
        finish();
    }


}