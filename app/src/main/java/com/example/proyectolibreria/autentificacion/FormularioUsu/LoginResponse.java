package com.example.proyectolibreria.autentificacion.FormularioUsu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class LoginResponse {


    @Expose
    public String token;

    @SerializedName("non_field_errors")
    @Expose
    public List<String> nonfielderrors;
}
