package com.gestion.tramite.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util
{

    public static String getFileExtension(String namefile) {

        int lastIndexOf = namefile.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return namefile.substring(lastIndexOf);
    }





    public static String getFixedString(Date valor, String pattern )
    {
        if (valor == null) return null;
        SimpleDateFormat nf = new SimpleDateFormat(pattern);
        return nf.format(valor);

    }




}
