package com.example.slafuente.tareasrecaderogson.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.slafuente.tareasrecaderogson.beans.Recado;
import com.example.slafuente.tareasrecaderogson.R;
import com.example.slafuente.tareasrecaderogson.adaptadores.RecadoAdapter;

import java.util.ArrayList;

public class TareaPorHacer extends AppCompatActivity {

    private ListView listView;
    private RecadoAdapter recadoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareaporhacer);

        getDatos();
    }

    public void getDatos(){

        Intent intent = getIntent();
        ArrayList<Recado> seleccionados = (ArrayList<Recado>) getIntent().getSerializableExtra("seleccionados");
        for (Recado p : seleccionados) {
            Log.e(getClass().getCanonicalName(), p.getDescripcion());
        }
        listView = (ListView)findViewById(R.id.lvListadoSelecc);
        recadoAdapter = new RecadoAdapter(TareaPorHacer.this, seleccionados);
        listView.setAdapter(recadoAdapter);
    }
}
