package com.example.proyectolibreria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyectolibreria.data.Volume;
import com.example.proyectolibreria.R;
import com.example.proyectolibreria.data.VolumesResponse;

public class BuscarLibros extends AppCompatActivity {

    private static final int ITEMS_PER_PAGE = 5;
    TextView idAutor, idPalabraClave;
    Button idBuscar, cargaMas;
    RecyclerView idRecyclerView;
    BookSearchViewModel vm;
    LiveData<VolumesResponse>data;

    int startIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idPalabraClave = findViewById(R.id.idPalabraClave);
        idAutor = findViewById(R.id.idAutor);
        idBuscar = findViewById(R.id.idBuscar);
        idRecyclerView = findViewById(R.id.idRecyclerView);
        cargaMas = findViewById(R.id.cargaMas);

        BookSearchResultsAdapter adapter = new BookSearchResultsAdapter();
        idRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        idRecyclerView.setAdapter(adapter);

        vm = new ViewModelProvider(this).get(BookSearchViewModel.class);
        vm.init();
        data = vm.getVolumesResponseLiveData();
        data.observe(this, (data)->{
            startIndex+=ITEMS_PER_PAGE;
            adapter.setResults(data.getItems());
        });

        //CON LAMBDA
        idBuscar.setOnClickListener((v)->{
            startIndex = 0;
            vm.searchVolumes(
                    idPalabraClave.getText().toString(),
                    idAutor.getText().toString(),
                    Integer.toString(startIndex));
        });

        //NORMAL
        cargaMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.searchVolumes(
                        idPalabraClave.getText().toString(),
                        idAutor.getText().toString(),
                        Integer.toString(startIndex));

            }
        });

    }
}