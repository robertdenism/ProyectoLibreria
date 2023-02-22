package com.example.proyectolibreria.autentificacion.FormularioUsu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.proyectolibreria.R;

public class UserPass extends AppCompatActivity {

EditText idUsu, idPass;
Button idBotonContinuar;
ProgressBar idProgressBar;
TextView ver;
ViewModelLogin vml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pass);

        idUsu = findViewById(R.id.idUsu);
        idPass = findViewById(R.id.idPass);
        idBotonContinuar = findViewById(R.id.idBotonEntrar);
        idProgressBar = findViewById(R.id.idProgressBar);
        ver = findViewById(R.id.ver);

        //recgoer viwemodel login
        vml = new ViewModelProvider(this).get(ViewModelLogin.class);
        vml.getLoginResponse().observe(this,(response)-> {
            idBotonContinuar.setVisibility(View.INVISIBLE);
            ver.setVisibility(View.VISIBLE);
            if(response.nonfielderrors.size()>0){
                ver.setText(response.nonfielderrors.get(0));

            }else{
                ver.setText("ok");
            }
        });

        //al hacer click

        idBotonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idBotonContinuar.setVisibility(View.INVISIBLE);
                ver.setVisibility(View.VISIBLE);
                idProgressBar.setVisibility(View.VISIBLE);
                vml.hacerLogin(idUsu.getText().toString(),idPass.getText().toString());
            }
        });

    }
    //public void iniciarSesion(){
      //  Intent isIntent = new Intent(this, AniadirPreguntas.class);
       // startActivity(isIntent);
    //}
}