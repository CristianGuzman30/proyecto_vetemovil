package com.example.vetemovil.interfaces;

import com.example.vetemovil.models.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuarioAPI {

    @GET("usuario/consultarUsuario/{id}")
    public Call<Usuario> find(@Path("id") String id);

    @POST("usuario/registrar")
    public Call<Usuario> enviarUsuario(@Body Usuario usuario);
}
