package com.gestion.tramite.util;


import java.io.File;
import java.io.IOException;

import com.gestion.tramite.controller.PersonaController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {


    /**
     *
     * @param path  ruta de acceso de almacenamiento de archivos
     * @param  fileName el nombre del archivo original
     * @return
     */

    static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static boolean upload(MultipartFile file, String path, String fileName){

        // generar nuevo nombre de archivo

        logger.info("ESTOY EN FileUtils::: "+path+";"+fileName);
        String realPath = path + "/" + fileName;

        logger.info("ESTOY EN FileUtils::: "+realPath);

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

            logger.info("SE COPIO EL ARCHIVO => "+dest.getName());
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
