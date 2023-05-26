package com.example.vetemovil.models;

public class Mascota {
    public String id;
    public String nombre;
    public String raza;
    public String peso;
    public String id_dueño;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getId_dueño() {
        return id_dueño;
    }

    public void setId_dueño(String id_dueño) {
        this.id_dueño = id_dueño;
    }
}
