package com.gestion.tramite.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion.tramite.entidad.*;
import com.gestion.tramite.service.FileService;
import com.gestion.tramite.service.FileServiceImp;
import com.gestion.tramite.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin( origins = "*" , methods = { RequestMethod. GET , RequestMethod. POST , RequestMethod. DELETE })
@RestController
@RequestMapping(value = "/fileController")
public class FileController {



    Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    FileService fileService;



    @PutMapping(value = "/{id}")
    public ResponseEntity<Archivo> update(@PathVariable("id") Long id, @RequestPart("archivo") Archivo a1, @RequestParam("file")MultipartFile file){
        a1.setId(id);
        String nomFileFinal = fileService.upload(file,a1);
        String urlFile="";

        if (nomFileFinal.length() > 0)///se subio OK el archivo
        {

            /////DEBO OBTENER LA URL DEL ARCHIVO SUBIDO
            List<FileModel> fileInfos = fileService.loadAll(a1.getDni().toString()).map(path -> {
                String filename = path.getFileName().toString();
                logger.info("filename: "+filename);
                String url = MvcUriComponentsBuilder.fromMethodName(FileController.class, "getFile",
                        path.getFileName().toString()).build().toString();
                return new FileModel(filename, url);
            }).collect(Collectors.toList());


            urlFile="";
            for (FileModel info:fileInfos)
            {
                if (nomFileFinal.equals(info.getName()))
                {
                    urlFile = info.getUrl();
                    break;
                }
            }

        }
        a1.setNombre(nomFileFinal);
        a1.setUrl(urlFile);
        Archivo res =  fileService.save(a1);
        if (res == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(res);
    }





    @RequestMapping(value = "/upload",method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Archivo> uploadFile(@RequestParam("file")MultipartFile file, @RequestPart("archivo") String archivo)
    {
        logger.info("ESTOY EN UPLOAD FILE");
        try
        {
                logger.info("JSON(Archivo):: "+archivo);
                Archivo a1 = new ObjectMapper().readValue(archivo, Archivo.class);
                String nomFileFinal = fileService.upload(file,a1);
                String urlFile="";

                if (nomFileFinal.length() > 0)///se subio OK el archivo
                {

                    /////DEBO OBTENER LA URL DEL ARCHIVO SUBIDO
                    List<FileModel> fileInfos = fileService.loadAll(a1.getDni().toString()).map(path -> {
                        String filename = path.getFileName().toString();
                        logger.info("filename: "+filename);
                        String url = MvcUriComponentsBuilder.fromMethodName(FileController.class, "getFile",
                                path.getFileName().toString()).build().toString();
                        return new FileModel(filename, url);
                    }).collect(Collectors.toList());


                    urlFile="";
                    for (FileModel info:fileInfos)
                    {
                        if (nomFileFinal.equals(info.getName()))
                        {
                            urlFile = info.getUrl();
                            break;
                        }
                    }

                }

                a1.setNombre(nomFileFinal);
                a1.setUrl(urlFile);
                Archivo res =  fileService.save(a1);
                if (res == null)
                {
                    return ResponseEntity.notFound().build();
                }
                return ResponseEntity.ok(res);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }










    /**@PostMapping("/uploads")
    public ResponseEntity<FileMessage> uploadFiles(@RequestParam("files")MultipartFile[] files){
        String message = "";
        try{
            List<String> fileNames = new ArrayList<>();

            Arrays.asList(files).stream().forEach(file->{
                fileService.save(file);
                fileNames.add(file.getOriginalFilename());
            });

            message = "Se subieron los archivos correctamente " + fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(new FileMessage(message));
        }catch (Exception e){
            e.printStackTrace();
            message = "Fallo al subir los archivos";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileMessage(message));
        }
    }**/



    @GetMapping("/files/{dni}")
    public ResponseEntity<List<FileModel>> getFiles(@PathVariable String dni){
        logger.info("ESTOY EN GETFILES__"+dni);
        List<FileModel> fileInfos = fileService.loadAll(dni).map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(FileController.class, "getFile",
                    path.getFileName().toString()).build().toString();
            return new FileModel(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }



    @GetMapping
    public ResponseEntity<List<Archivo>> listFiles()
    {
        List<Archivo> files = new ArrayList<>();
        files = fileService.listAllFiles();
        if (files.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(files);
    }




    @GetMapping("/filesByGestion/{idGestionTramite}")
    public ResponseEntity<List<Archivo>> getFilesPorGestionTramite(@PathVariable Long idGestionTramite)
    {
        logger.info("ESTOY EN getFilesPorGestionTramite_"+idGestionTramite);
        List<Archivo> listFile = fileService.buscarFiles(idGestionTramite);
        /**if (listFile.size() > 0)
        {
            ////OBTENGO LA LISTA DE URL Y ARCHIVOS DE LA CARPETA
            Integer dni= listFile.get(0).getDni();
            logger.info("dni: "+dni);

            List<FileModel> fileInfos = fileService.loadAll(dni.toString()).map(path -> {
                String filename = path.getFileName().toString();
                logger.info("filename: "+filename);
                String url = MvcUriComponentsBuilder.fromMethodName(FileController.class, "getFile",
                        path.getFileName().toString()).build().toString();
                return new FileModel(filename, url);
            }).collect(Collectors.toList());

            logger.info("listFile.size(): "+listFile.size());
            logger.info("fileInfos.size(): "+fileInfos.size());

            int index =0;
            for (Archivo arch:listFile)
            {
                for (FileModel info:fileInfos)
                {
                    logger.info("COMPARO: "+arch.getNombre());
                    logger.info("COMPARO: "+info.getName());
                    if (arch.getNombre().equals(info.getName()))
                    {
                        listFile.get(index).setUrl(info.getUrl());
                        break;
                    }
                }
                index++;
            }
        }**/
        return ResponseEntity.status(HttpStatus.OK).body(listFile);
    }



   /** @GetMapping("/files")
    public ResponseEntity<List<FileModel>> getFiles(){
        logger.info("ENTROO EN getFiles");
        List<FileModel> fileInfos = fileService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(FileController.class, "getFile",
                    path.getFileName().toString()).build().toString();
            return new FileModel(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }
**/









    @GetMapping("/file/{filename}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename){
        Resource file = fileService.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\""+file.getFilename() + "\"").body(file);
    }

    /**@GetMapping("/delete/{filename}")
    public ResponseEntity<FileMessage> deleteFile(@PathVariable String filename) {
        String message = "";
        try {
            message = fileService.deleteFile(filename);
            return ResponseEntity.status(HttpStatus.OK).body(new FileMessage(message));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileMessage(message));
        }
    }**/



     /*   @DeleteMapping("/delete")
    public boolean deleteFile(@RequestPart("archivo") Archivo archivo) {

            logger.info("ESTOY EN deleteFile ");

        return fileService.deleteFile(archivo);

    }
*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Archivo>  deleteFile(@PathVariable("id") Long id){

        System.out.println("estoy en deleteArchivo");

        Archivo cliDel= fileService.deleteFile(id);

        System.out.println("estoy en deleteArchivo 2");
        if (cliDel == null)
        {
            return ResponseEntity.notFound().build();
        }

        System.out.println("estoy en deleteArchivo 3");

        return ResponseEntity.ok(cliDel);
    }



}
