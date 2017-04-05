package com.example.slafuente.tareasrecaderogson.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.slafuente.tareasrecaderogson.beans.Recado;
import com.example.slafuente.tareasrecaderogson.tareas.ObtenerRecado;
import com.example.slafuente.tareasrecaderogson.R;
import com.example.slafuente.tareasrecaderogson.adaptadores.RecadoAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String cadenaURL = "http://elrecadero-ebtm.rhcloud.com/ObtenerListaRecados";
    private List<Recado> listaRecados = null;
    private  List<Recado> listaRecadosSeleccionados;
    private ObtenerRecado ob =  null;
    private RecadoAdapter adapter = null;
    private Button bInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instanciamos boton y list view
        bInicio = (Button)findViewById(R.id.bInicio);
        ListView lv = (ListView)findViewById(R.id.lvListado);

        ob = new ObtenerRecado(this);
        listaRecadosSeleccionados = new ArrayList<Recado>();

        if (listaRecados == null) {
            listaRecados = new ArrayList<Recado>();
        }
        adapter = new RecadoAdapter(this, listaRecados);


        bInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //llamamos a nuestro clase asynctask
                    ob.execute(cadenaURL);
                } catch (Throwable t) {
                    Log.e(getClass().getCanonicalName(), "Error parseando recado", t);
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infla el menu y lo anade a la action bar
        getMenuInflater().inflate(R.menu.mimenu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id) {
            case R.id.menu_porhacer:
                //Vamos a la segunda actividad mostrando los elementos seleccionados
                Intent intent = new Intent(getApplicationContext(), TareaPorHacer.class);
                intent.putExtra("seleccionados", (Serializable) listaRecadosSeleccionados);
                startActivity(intent);
                //Notificamos al adapter
                adapter.notifyDataSetChanged();
                break;
            case R.id.menu_limpiar:
                listaRecados.clear();
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Seteamos la lista de recados en el listview principal
    public void setLista(List<Recado> lr) {
            ListView lv = (ListView)findViewById(R.id.lvListado);

            //seteamos en el adapter la lista de tareas recogida
            adapter = new RecadoAdapter(this, lr);
            lv.setAdapter(adapter);

            //deshabilitamos el boton de buscar tareas
            bInicio.setEnabled(false);
        }
}

