package com.example.proyectolibreria.autentificacion.FormularioUsu;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionRepositorio {

    private static final String URL=("http://192.168.20.209:8000");
    public static QuestionRepositorio instancia;
    private ApiAutentificacion accesoApi;
    private QuestionRepositorio(){
        accesoApi=new retrofit2.Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiAutentificacion.class);

    }
    public static QuestionRepositorio getInstance() {
        if (instancia == null) {
            instancia = new QuestionRepositorio();
        }
        return instancia;
    }

    public Call<LoginResponse> login(String username, String password){
        return accesoApi.login(new LoginCredencial(username,password));
    }

}
