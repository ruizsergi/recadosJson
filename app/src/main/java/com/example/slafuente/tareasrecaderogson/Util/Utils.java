package com.example.slafuente.tareasrecaderogson.Util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by slafuente on 04/04/2017.
 */

public class Utils {

    public static DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    /**
     * Formateamos la fecha para que sea mas legible
     * @param fecha
     * @return
     */
    public static String formatFecha(Object fecha) {
        if (formatter == null) {
            formatter = DateFormat.getDateInstance();
        }
        return ((fecha == null) ? "" : formatter.format(fecha));
    }
}
