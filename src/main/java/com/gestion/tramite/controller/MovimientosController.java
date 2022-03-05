package com.gestion.tramite.controller;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.gestion.tramite.entidad.FileModel;
import com.gestion.tramite.entidad.Movimientos;

import com.gestion.tramite.service.FileService;
import com.gestion.tramite.service.MovimientosService;
import com.gestion.tramite.util.FileUtils;
import com.gestion.tramite.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.File;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@CrossOrigin( origins = "*" , methods = { RequestMethod. GET , RequestMethod. POST , RequestMethod.DELETE ,RequestMethod.PUT })
@RestController
@RequestMapping(value = "/movimientos")
public class MovimientosController
{

    Logger logger = LoggerFactory.getLogger(MovimientosController.class);

    @Value("${web.upload-path}")
    private String path;


    @Autowired
    FileService fileService;

    @Autowired
    private MovimientosService service;

    @GetMapping
    public ResponseEntity<List<Movimientos>> listAllMovimientos(@PathVariable("idContabilidad") Long idContabilidad)
    {
        List<Movimientos> a1 = service.listAllMovimientos(idContabilidad);
        if (a1.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(a1);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Movimientos> getMovimiento(@PathVariable("id") Long id)
    {
        Movimientos mov =  service.getMovimiento(id);
        if (null==mov){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mov);
    }


    /**@PostMapping
    public ResponseEntity<Movimientos> nuevoMovimiento(@RequestBody Movimientos mov)
    {
        Movimientos a1 =  service.nuevoMovimiento(mov);
        return ResponseEntity.status(HttpStatus.CREATED).body(a1);
    }**/


    @PostMapping
    public ResponseEntity<Movimientos> createMovimientos(@RequestBody List<Movimientos> mov)
    {
        Movimientos a1 =  service.createMovimientos(mov);
        return ResponseEntity.status(HttpStatus.CREATED).body(a1);
    }



    @PutMapping(value = "/{id}")
    public ResponseEntity<Movimientos> updateMovimiento(@PathVariable("id") Long id, @RequestBody Movimientos mov){

        System.out.println("estoy en updateContabilidad");
        System.out.println("estoy en updateContabilidad id"+id);
        mov.setId(id);
        Movimientos cliBD =  service.updateMovimiento(mov);
        if (cliBD == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliBD);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Movimientos>  deleteMovimiento(@PathVariable("id") Long id){

        System.out.println("estoy en deleteContabilidad");

        Movimientos cont=service.deleteMovimiento(id);

        System.out.println("estoy en deleteContabilidad 2");
        if (cont == null)
        {
            return ResponseEntity.notFound().build();
        }

        System.out.println("estoy en deleteContabilidad 3");

        return ResponseEntity.ok(cont);
    }



    @ResponseBody
    @RequestMapping(value = "/actualizarMovimiento",method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Movimientos> actualizarMovimiento(@RequestParam("fileMoviento") MultipartFile fileMoviento,
                                                     @RequestPart("mov") String dMov)

    {
        logger.info("%%%%%%%%%%%%%%%%%%%ESTOY EN actualizarMovimiento");
        String localPath=null,nomFileFinal1=null;
        boolean band;
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            logger.info("JSON(MOVMIENTOS):: "+dMov);
            Movimientos mov = mapper.readValue(dMov, Movimientos.class);


            logger.info("NOMBRE ARCHIVO_"+fileMoviento.getOriginalFilename());
            String extFotoFrente = getFileExtension(fileMoviento.getOriginalFilename());
            logger.info("EXTENSION ARCHIVO_FOTO_FRENTE:: "+extFotoFrente);

            Integer dniCliente = mov.getContabilidad().getGestionTramite().getPersona().getDni();
            localPath=path+"/"+dniCliente;
            logger.info("localPath:: "+localPath);
            File direc= new File(localPath);
            if (!direc.exists())
            {
                if (direc.mkdirs())
                {
                    System.out.println("SE CREO EL DIRECTOTIIO");
                }
            }

            Date fechaActual=new Date();
            System.out.println(Util.getFixedString(fechaActual, "yyyyMMdd-HHmmss"));

            nomFileFinal1= Util.getFixedString(fechaActual, "yyyyMMdd_HHmmss")+"_"+"Movimiento_"+mov.getId()+"_"+getFileExtension(fileMoviento.getOriginalFilename());
            logger.info("nomFileFOTO1: "+nomFileFinal1);
            band = FileUtils.upload(fileMoviento, localPath, nomFileFinal1);
            mov.setUrlArchivo(nomFileFinal1);



            String urlFile="";


            if(band)
            {

                /////////////////////////////OBTENGO LA URL DE LOS ARCHIVOS/////////////////////////////////////////////
                /////DEBO OBTENER LA URL DEL ARCHIVO SUBIDO
                List<FileModel> fileInfos = fileService.loadAll(dniCliente.toString()).map(path -> {
                    String filename = path.getFileName().toString();
                    logger.info("filename: "+filename);
                    String url = MvcUriComponentsBuilder.fromMethodName(FileController.class, "getFile",
                            path.getFileName().toString()).build().toString();
                    return new FileModel(filename, url);
                }).collect(Collectors.toList());


                urlFile = obtenerRuta(fileInfos, nomFileFinal1);
                //////////////////////////////FIN URL ARCHIVOS////////////////////////////////////
                logger.info("urlFile::"+urlFile);

                ////GRABO LOS DATOS EN LA BASE
                mov.setUrlArchivo(urlFile);


                Movimientos existeMov=service.getMovimiento(mov.getId());
                if (Objects.nonNull(existeMov))/////ES UNA MODIFICACION
                {
                    ////DEBO BORRAR LOS CONCTACTO Y ACTUALIZAR LA IMAGEN y el CLIENTE
                    logger.info("ES UNA ACTUALIZACION DE MOVIMIENTO:: "+mov.getId());
                     mov = service.updateMovimiento(mov);

                }
                else////ES UN ALTA DE CLIENTE
                {
                    mov =  service.nuevoMovimiento(mov);
                    logger.info("DIO DE ALTA EL MOVIMIENTO");
                }//////if (Objects.nonNull(existePer))/
                return ResponseEntity.status(HttpStatus.CREATED).body(mov);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);




        } catch (Exception e) {
            logger.error("DIO ERROR");
            e.printStackTrace();
            /////////////DEBO HACER UN ROLLBACK///////////////
            File f1;
            if (Objects.nonNull(localPath))
            {
                logger.error("PATH: "+localPath+"/"+nomFileFinal1);
                f1= new File(localPath+"/"+nomFileFinal1);
                f1.delete();
            }



            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }





    private String getFileExtension(String namefile) {

        int lastIndexOf = namefile.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return namefile.substring(lastIndexOf);
    }




    public String obtenerRuta(List<FileModel> lista, String nameFile)
    {
        String url="";
        for (FileModel info:lista)
        {
            if (nameFile.equals(info.getName()))
            {
                url = info.getUrl();
                break;
            }
        }
        return url;
    }









}
