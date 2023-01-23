package com.example.proyectolibreria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyectolibreria.data.Volume;
import com.example.proyectolibreria.R;
import com.example.proyectolibreria.data.VolumesResponse;

public class BuscarLibros extends AppCompatActivity {

    TextView idAutor, idPalabraClave;
    Button idBuscar;
    RecyclerView idRecyclerView;
    BookSearchViewModel vm;
    LiveData<VolumesResponse>data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idPalabraClave = findViewById(R.id.idPalabraClave);
        idAutor = findViewById(R.id.idAutor);
        idBuscar = findViewById(R.id.idBuscar);
        idRecyclerView = findViewById(R.id.idRecyclerView);

        BookSearchResultsAdapter adapter = new BookSearchResultsAdapter();
        idRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        idRecyclerView.setAdapter(adapter);

        vm = new ViewModelProvider(this).get(BookSearchViewModel.class);
        vm.init();
        data = vm.getVolumesResponseLiveData();
        data.observe(this, (data)->{
            adapter.setResults(data.getItems());
        });

        idBuscar.setOnClickListener((v)->{
            vm.searchVolumes(idPalabraClave.getText().toString(),idAutor.getText().toString());
        });

    }
}