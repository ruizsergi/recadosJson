package com.example.slafuente.tareasrecaderogson.tareas;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.slafuente.tareasrecaderogson.actividades.MainActivity;
import com.example.slafuente.tareasrecaderogson.beans.Recado;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by slafuente on 04/04/2017.
 */

public class ObtenerRecado extends AsyncTask<String, Void, List<Recado>> {

    private Context context;
    private List<Recado> listaRecado = null;
    private InputStream is = null;
    private URL url = null;
    private HttpURLConnection httpConn = null;
    private Reader reader = null;

    public ObtenerRecado(Context c) {
        context = c;
    }

    @Override
    protected List<Recado> doInBackground(String... strings) {

        try {
            url = new URL(strings[0]);
            httpConn = (HttpURLConnection) url.openConnection();
            int responseCode = httpConn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                is = httpConn.getInputStream();
                reader = new InputStreamReader(is);
                listaRecado = readRecados(reader);
                //cerramos el reader una vez utilizado
                reader.close();

            }
        }catch (Exception e) {
            Log.e("MENSAJE" , "ERROR obteniendo json", e);
        } finally {
            httpConn.disconnect();
       }
        return listaRecado;
    }

    /**
     * Lee los recados desde el json y los devuelve en forma de lista.
     * @param jsonReader
     * @return lista de recados
     * @throws IOException
     */
    public List<Recado> readRecados(Reader jsonReader) throws IOException{
        List<Recado> recados = new ArrayList<Recado>();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        Type type = new TypeToken<List<Recado>>(){}.getType();
        recados = Arrays.asList(gson.fromJson(jsonReader, Recado[].class));

        //ordeno recados por fecha y hora
        Collections.sort(recados);

        return recados;
    }

    @Override
    protected void onPostExecute(List<Recado> recados) {
        //Al acabar doInBackground, mandamos lista recados a main activity para que los pinte
        MainActivity ma = (MainActivity) context;
        ma.setLista(recados);

    }
}
