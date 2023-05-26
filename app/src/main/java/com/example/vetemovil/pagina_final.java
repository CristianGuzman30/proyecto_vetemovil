package com.example.vetemovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vetemovil.interfaces.MascotaAPI;
import com.example.vetemovil.interfaces.UsuarioAPI;
import com.example.vetemovil.models.Mascota;
import com.example.vetemovil.models.Usuario;

import org.w3c.dom.Text;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class pagina_final extends AppCompatActivity {

    Button menu;

    TextView nombreUsuario;
    TextView telefono;
    TextView nombreMascota;
    TextView peso;
    TextView raza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_final);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String usuario = sharedPreferences.getString("usuario", "");
        nombreUsuario = findViewById(R.id.textView31);
        telefono = findViewById(R.id.textView32);
        nombreMascota = findViewById(R.id.textView35);
        peso = findViewById(R.id.textView36);
        raza = findViewById(R.id.textView38);

        find(usuario);
        menu = findViewById(R.id.button15);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu();
            }
        });
    }

    public void menu(){
        Intent menu = new Intent(this, pagina4.class);
        startActivity(menu);
        finish();
    }

    private void find(String codigo){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://vetemovil.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        UsuarioAPI UsuarioAPI = retrofit.create(UsuarioAPI.class);
        Call<Usuario> call = UsuarioAPI.find(codigo);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                try{
                    if(response.isSuccessful()){
                        Usuario p = response.body();
                        nombreUsuario.setText(p.getNombre());
                        telefono.setText(p.getTelefono());
                        findMascota(p.id);
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                    Toast.makeText(pagina_final.this, "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                if (t instanceof IOException) {
                    // Error de red, como falta de conexión o tiempo de espera agotado
                    Toast.makeText(pagina_final.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    // Otro tipo de error, como un error en la respuesta del servidor
                    Toast.makeText(pagina_final.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void findMascota(String codigo){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://vetemovil.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        MascotaAPI mascotaAPI = retrofit.create(MascotaAPI.class);
        Call<Mascota> call = mascotaAPI.findMascota(codigo);
        call.enqueue(new Callback<Mascota>() {
            @Override
            public void onResponse(Call<Mascota> call, Response<Mascota> response) {
                try{
                    if(response.isSuccessful()){
                        Mascota p = response.body();
                        nombreMascota.setText(p.getNombre());
                        peso.setText(p.getPeso());
                        raza.setText(p.getRaza());
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                    Toast.makeText(pagina_final.this, "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Mascota> call, Throwable t) {
                if (t instanceof IOException) {
                    // Error de red, como falta de conexión o tiempo de espera agotado
                    Toast.makeText(pagina_final.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    // Otro tipo de error, como un error en la respuesta del servidor
                    Toast.makeText(pagina_final.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}