package com.gestion.tramite.util;

import com.gestion.tramite.ServicioGestionTramiteApplication;
import org.springframework.boot.SpringApplication;

import java.io.File;

public class Test {

    public static void main(String[] args) {

        String localPath="C:/IntelliJ/gestionTramiteDocumentos/gestionCliente";
        String dni="25236748";

        System.out.println(dni.substring(dni.length()-3,dni.length()));
        File direc= new File(localPath+"/"+dni);
        if (!direc.exists())
        {
            if (direc.mkdirs())
            {
                System.out.println("SE CREO EL DIRECTOTIIO");
            }
        }


    }
}