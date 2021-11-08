package com.gestion.tramite.util;


import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

    /**
     *
     * @param path  ruta de acceso de almacenamiento de archivos
     * @param  fileName el nombre del archivo original
     * @return
     */
    public static boolean upload(MultipartFile file, String path, String fileName){

        // generar nuevo nombre de archivo
        String realPath = path + "/" + fileName;

        // usa el nombre del archivo original
        // String realPath = path + "/" + fileName;

        File dest = new File(realPath);

        // Determinar si el directorio padre del archivo existe
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }

        try {
            // Guardar el archivo
            file.transferTo(dest);
            return true;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
}
