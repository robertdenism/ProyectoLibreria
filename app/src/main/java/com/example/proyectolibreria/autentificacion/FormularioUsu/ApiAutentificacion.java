package com.example.proyectolibreria.autentificacion.FormularioUsu;

import com.example.proyectolibreria.autentificacion.FormularioUsu.LoginCredencial;
import com.example.proyectolibreria.libreria.data.VolumesResponse;


import javax.security.auth.login.LoginException;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiAutentificacion {

    @POST("/polls/api-token-auth")
    Call<LoginResponse> login(
            @Body LoginCredencial credencial
    );


}
