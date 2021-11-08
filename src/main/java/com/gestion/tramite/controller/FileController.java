package com.gestion.tramite.controller;


import com.gestion.tramite.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@CrossOrigin( origins = "*" , methods = { RequestMethod. GET , RequestMethod. POST })
@RestController
@RequestMapping(value = "/fileController")
public class FileController {



    Logger logger = LoggerFactory.getLogger(FileController.class);




    /**
     *
     * @return
     */
    @ResponseBody
    //@RequestMapping(value = "/img",method = RequestMethod.POST)
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String upload(@RequestParam("file")MultipartFile file,@RequestParam("fileName")String fileName ){
        logger.info("ESTOY EN upload_"+fileName);
        logger.info("NOMBRE ARCHIVO_"+file.getOriginalFilename());
        String extension = getFileExtension(file.getOriginalFilename());
        logger.info("EXTENSION ARCHIVO:: "+extension);

        String localPath="C:/IntelliJ/gestionTramiteDocumentos";
        String nameFileFinal= fileName+getFileExtension(file.getOriginalFilename());

        if(FileUtils.upload(file, localPath, fileName))
        {
            return "OK";
        }
        return "ERROR";
    }







    @GetMapping("/download/{fileName}")// Presta atención a la adquisición de datos
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileName") String fileName, HttpServletRequest request) {
        // fileName // Establecer el nombre del archivo y obtenerlo de la ruta de solicitud de acuerdo con las necesidades comerciales
        logger.info("ESTOY EN download_"+fileName);
        if (fileName != null) {

            // Establecer ruta de archivo
            String realPath = "c:\\IntelliJ\\IMAGENES\\";
            // Try to determine file's content type
            String contentType = null;
            try {
                contentType = request.getServletContext().getMimeType(realPath+fileName);
                InputStreamResource resource = new InputStreamResource(new FileInputStream(realPath+fileName));


                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                        .body(resource);

            } catch (Exception ex) {
                logger.info("Could not determine file type.");
            }



        }
        return null;
    }



    private String getFileExtension(String namefile) {

        int lastIndexOf = namefile.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return namefile.substring(lastIndexOf);
    }



}
