package com.example.slafuente.tareasrecaderogson.Adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.slafuente.tareasrecaderogson.Beans.Recado;
import com.example.slafuente.tareasrecaderogson.R;
import com.example.slafuente.tareasrecaderogson.Util.Utils;

import java.util.List;

/**
 * Created by slafuente on 04/04/2017.
 */

public class RecadoAdapter extends ArrayAdapter<Recado> {


    public RecadoAdapter(Context context, List<Recado> recados) {
        super(context, 0, recados);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Recado recado = getItem(position);

        //inflamos si no esta creado
        if (convertView ==  null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.recado, parent, false);
        }

        //Asigno objetos
        TextView tv =(TextView)convertView.findViewById(R.id.textRecado);
        TextView tvFecha = (TextView)convertView.findViewById(R.id.textFecha);
        CheckBox cb = (CheckBox)convertView.findViewById(R.id.checkBox);
        //seteo valores
        tv.setText(recado.getDescripcion());
        String fechaFormateada = Utils.formatFecha(recado.getFecha_hora());
        tvFecha.setText(fechaFormateada);

        if (recado == null)
            cb.setEnabled(false);

        return convertView;
    }


}

