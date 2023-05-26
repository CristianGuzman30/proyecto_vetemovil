package com.example.vetemovil.interfaces;

import com.example.vetemovil.models.Mascota;
import com.example.vetemovil.models.Perfil;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MascotaAPI {
    @GET("mascotas/consultarMascota/{id}")
    public Call<Mascota> findMascota(@Path("id") String id);
}
