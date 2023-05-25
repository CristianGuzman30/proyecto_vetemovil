package com.example.vetemovil.interfaces;

import com.example.vetemovil.models.Perfil;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PerfilAPI {
    @GET("perfil/consultar/{id}")
    public Call<Perfil> find(@Path("id") String id);
}
