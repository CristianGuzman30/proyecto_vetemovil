package com.example.vetemovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vetemovil.interfaces.UsuarioAPI;
import com.example.vetemovil.models.Usuario;

import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class MainActivity extends AppCompatActivity {

    Button botonInicioSesion;
    Button registrarUsuario;
    EditText usuario;
    EditText contrasena;
    String textUsuario;
    String textContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonInicioSesion = findViewById(R.id.button);
        registrarUsuario = findViewById(R.id.button3);
        usuario = findViewById(R.id.editTextTextPersonName);
        contrasena = findViewById(R.id.editTextTextPersonName2);

        botonInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textUsuario = usuario.getText().toString();
                textContrasena = contrasena.getText().toString();

                if(textUsuario.isEmpty() || textContrasena.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Todos los campos son requeridos", Toast.LENGTH_SHORT).show();
                } else {
                    find(textUsuario);
                }
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
                        String contraUsuario = p.getContrasena();
                        validacionUsuario(contraUsuario);
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                    Toast.makeText(MainActivity.this, "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                if (t instanceof IOException) {
                    // Error de red, como falta de conexión o tiempo de espera agotado
                    Toast.makeText(MainActivity.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    // Otro tipo de error, como un error en la respuesta del servidor
                    Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void validacionUsuario(String contraUsuario){
        if(textUsuario != null){
            if (BCrypt.checkpw(textContrasena, contraUsuario)) {
                Intent principal = new Intent(this, pagina4.class);
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("usuario", textUsuario);
                editor.apply();

                startActivity(principal);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }
    }
}