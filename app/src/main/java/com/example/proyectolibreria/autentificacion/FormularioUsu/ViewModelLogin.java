package com.example.proyectolibreria.autentificacion.FormularioUsu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.nio.channels.InterruptedByTimeoutException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelLogin extends ViewModel {

    private static final int TEST_VAL = 200;
    private static final int SUCCESS_PROBABILITY = 200;
    private MutableLiveData<LoginResponse> mlData;

    public ViewModelLogin(){

    }

    public LiveData<LoginResponse> getLoginResponse(){
        return mlData;
    }

    public void hacerLogin(String user,String pass){
        new Thread(()->{
            QuestionRepositorio repo = QuestionRepositorio.getInstance();

            repo.login(user,pass).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    mlData.postValue(response.body());
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    LoginResponse response = new LoginResponse();
                    response.token="";
                    response.nonfielderrors = new ArrayList<String>();
                    response.nonfielderrors.add("Ha ocurrido un error");
                    mlData.postValue(response);
                }
            });

        }).start();

    }

}
