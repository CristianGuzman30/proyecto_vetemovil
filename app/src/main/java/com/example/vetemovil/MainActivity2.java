package com.example.vetemovil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vetemovil.interfaces.PerfilAPI;
import com.example.vetemovil.models.Perfil;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity {

    TextView prueba;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        prueba = findViewById(R.id.textView3);
        boton = findViewById(R.id.button7);
        int numero = 4;
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                find(Integer.toString(numero));
            }
        });

    }

    private void find(String codigo){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://vetemovil.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        PerfilAPI perfilAPI = retrofit.create(PerfilAPI.class);
        Call<Perfil> call = perfilAPI.find(codigo);
        call.enqueue(new Callback<Perfil>() {
            @Override
            public void onResponse(Call<Perfil> call, Response<Perfil> response) {
                try{
                    if(response.isSuccessful()){
                        Perfil p = response.body();
                        prueba.setText(p.getDescripcion());
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                    Toast.makeText(MainActivity2.this, "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Perfil> call, Throwable t) {
                if (t instanceof IOException) {
                    // Error de red, como falta de conexión o tiempo de espera agotado
                    Toast.makeText(MainActivity2.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    // Otro tipo de error, como un error en la respuesta del servidor
                    Toast.makeText(MainActivity2.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}