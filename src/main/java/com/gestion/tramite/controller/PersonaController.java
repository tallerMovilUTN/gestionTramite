package com.gestion.tramite.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion.tramite.entidad.Contacto;
import com.gestion.tramite.entidad.FileModel;
import com.gestion.tramite.entidad.Persona;
import com.gestion.tramite.service.ContactoService;
import com.gestion.tramite.service.FileService;
import com.gestion.tramite.service.PersonaService;
import com.gestion.tramite.util.FileUtils;
import com.gestion.tramite.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@CrossOrigin( origins = "*" , methods = { RequestMethod. GET , RequestMethod. POST,RequestMethod.PUT })
@RestController
@RequestMapping(value = "/personas")
public class PersonaController
{
    @Autowired
    PersonaService service;

    @Autowired
    FileService fileService;

    @Autowired
    ContactoService serContacto;



    @Value("${web.upload-path}")
    private String path;


    //PersonaRepositorio repoPer;

    //ContactoRepositorio repoContac;


    Logger logger = LoggerFactory.getLogger(PersonaController.class);


    @GetMapping
    public ResponseEntity<List<Persona>> listCliente()
    {
        List<Persona> cat = new ArrayList<>();
        cat = service.listAllCliente();
        if (cat.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cat);
    }










    @GetMapping(value = "/{id}")
    public ResponseEntity<Persona> getPersona(@PathVariable("id") Long id)
    {
        logger.info("ESTOY EN getPersona-"+id);
        Persona cat =  service.getPersona(id);
        if (null==cat){
            return ResponseEntity.notFound().build();
        }
        logger.info("ESTOY EN getCliente-"+cat.getApellido());

        return ResponseEntity.ok(cat);
    }


    @GetMapping(value = "/getPersonaPorDni/{dni}")
    public ResponseEntity<Persona> getPersona(@PathVariable("dni") int dni)
    {
        logger.info("ESTOY EN getPersona-"+dni);
        Persona cat =  service.getPersonaPorDni(dni);
        if (null==cat){
            return ResponseEntity.notFound().build();
        }
        logger.info("ESTOY EN getPersonaPorDni-"+cat.getApellido());

        return ResponseEntity.ok(cat);
    }




    @GetMapping("/file/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename,@RequestParam("carpeta") String carpeta) {
        logger.info("ESTOY FILE: "+filename);
        Resource file = service.load(filename,carpeta);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }





    @PostMapping
    public ResponseEntity<Persona> createPersona(@RequestBody Persona cli)
    {
        Persona a1 =  service.createPersona(cli);
        return ResponseEntity.status(HttpStatus.CREATED).body(a1);
    }



    @PostMapping(value = "/altaPersona", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public  ResponseEntity<Persona> altaPersona(@RequestPart("persona") Persona cli,@RequestParam("fotoFrente") MultipartFile file1, @RequestParam("fotoDorso") MultipartFile file2){
        logger.info("ESTOY EN CREAR PERSONA ");

        logger.info("NOMBRE ARCHIVO_fotoFrente: "+file1.getOriginalFilename());
        logger.info("NOMBRE ARCHIVO_fotoDorso: "+file2.getOriginalFilename());



        //String localPath="C:/IntelliJ/gestionTramiteDocumentos";
        //String nameFileFinal= fileName+getFileExtension(file.getOriginalFilename());
        String extension = getFileExtension(file1.getOriginalFilename());
        logger.info("EXTENSION ARCHIVO:: "+extension);
        String fileName1 = cli.getDni()+"-fotoFrente"+extension;
        String fileName2 = cli.getDni()+"-fotoDorso"+extension;
        cli.setIdfotoDorso(path+"/"+fileName1);
        cli.setIdfotoFrente(path+"/"+fileName2);


        logger.info("RUTA ARCHIVO1:: "+cli.getIdfotoFrente());
        logger.info("RUTA ARCHIVO2:: "+cli.getIdfotoDorso());

        Persona a1 =  service.createPersona(cli);

        FileUtils.upload(file1, path, fileName1);
        FileUtils.upload(file2, path, fileName2);
        return ResponseEntity.status(HttpStatus.CREATED).body(a1);
    }




    /**
     *
     * @return
     */
    @ResponseBody
    //@RequestMapping(value = "/img",method = RequestMethod.POST)
    @RequestMapping(value = "/upload",method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Persona> upload(@RequestParam("fotoFrente") MultipartFile fotoFrente,
                         @RequestParam("fotoDorso" )MultipartFile fotoDorso,
                         @RequestPart("persona") String dPersona,
                         @RequestPart("padre") String dPadre,
                         @RequestPart("madre") String dMadre,
                         @RequestPart("abueloPat") String dabueloPat,
                         @RequestPart("abuelaPat") String dabuelaPat,
                         @RequestPart("abueloMat") String dabueloMat,
                         @RequestPart("abuelaMat") String dabuelaMat
                         )

    {
        logger.info("ESTOY EN upload");
        logger.info("NOMBRE ARCHIVO_"+fotoFrente.getOriginalFilename());
        String extFotoFrente = getFileExtension(fotoFrente.getOriginalFilename());
        String extFotoDorso = getFileExtension(fotoDorso.getOriginalFilename());
        logger.info("EXTENSION ARCHIVO_FOTO_FRENTE:: "+extFotoFrente);
        logger.info("EXTENSION ARCHIVO_FOTO_DORSO:: "+extFotoDorso);


        logger.info("JSON(PERSONA):: "+dPersona);
        logger.info("JSON(PADRE):: "+dPadre);
        logger.info("JSON(MADRE):: "+dMadre);
        logger.info("JSON(ABUELO PAT):: "+dabueloPat);
        logger.info("JSON(ABUELA PAT):: "+dabuelaPat);
        logger.info("JSON(ABUELO MAT):: "+dabueloMat);
        logger.info("JSON(ABUELA MAT):: "+dabuelaMat);


        Persona cli = null;
        Contacto padre,madre,abueloPat,abuelaPat, abueloMat,abuelaMat;
        Persona a1=null;
        Contacto pa=null,ma=null,abloPat=null,ablaPat=null,abloMat=null,ablaMat=null;


        String localPath=null,nomFileFinal1=null,nomFileFinal2=null;

        try {
            cli = new ObjectMapper().readValue(dPersona, Persona.class);
            padre = new ObjectMapper().readValue(dPadre, Contacto.class);
            madre = new ObjectMapper().readValue(dMadre, Contacto.class);
            abueloPat = new ObjectMapper().readValue(dabueloPat, Contacto.class);
            abuelaPat = new ObjectMapper().readValue(dabuelaPat, Contacto.class);
            abueloMat = new ObjectMapper().readValue(dabueloMat, Contacto.class);
            abuelaMat = new ObjectMapper().readValue(dabuelaMat, Contacto.class);

            localPath=path+"/"+cli.getDni();
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

            nomFileFinal1= cli.getDni()+"_"+Util.getFixedString(fechaActual, "yyyyMMdd_HHmmss")+"_"+"FotoFrente"+getFileExtension(fotoFrente.getOriginalFilename());
            nomFileFinal2= cli.getDni()+"_"+Util.getFixedString(fechaActual, "yyyyMMdd_HHmmss")+"_"+"FotoDorso"+getFileExtension(fotoDorso.getOriginalFilename());
            logger.info("nomFileFOTO1: "+nomFileFinal1);
            logger.info("nomFileFOTO2: "+nomFileFinal2);
            logger.info("APELLIDO: "+cli.getApellido()+" "+cli.getNombre());
            boolean band1 = FileUtils.upload(fotoFrente, localPath, nomFileFinal1);
            boolean band2 = FileUtils.upload(fotoDorso, localPath, nomFileFinal2);
            cli.setIdfotoFrente(nomFileFinal1);
            cli.setIdfotoDorso(nomFileFinal2);



            String urlFotoFrente="";
            String urlFotoDorso="";

            if(band1 && band2)
            {

                    /////////////////////////////OBTENGO LA URL DE LOS ARCHIVOS/////////////////////////////////////////////
                    /////DEBO OBTENER LA URL DEL ARCHIVO SUBIDO

                    //List<FileModel> fileInfos = fileService.loadAll(a1.getDni().toString()).map(path -> {//PRUEBA DE ERROR

                    List<FileModel> fileInfos = fileService.loadAll(cli.getDni().toString()).map(path -> {
                        String filename = path.getFileName().toString();
                        logger.info("filename: "+filename);
                        String url = MvcUriComponentsBuilder.fromMethodName(FileController.class, "getFile",
                                path.getFileName().toString()).build().toString();
                        return new FileModel(filename, url);
                    }).collect(Collectors.toList());


                    urlFotoFrente = obtenerRuta(fileInfos, nomFileFinal1);
                    urlFotoDorso = obtenerRuta(fileInfos, nomFileFinal2);
                    //////////////////////////////FIN URL ARCHIVOS////////////////////////////////////
                logger.info("urlFotoFrente::"+urlFotoFrente);
                logger.info("urlFotoDorso::"+urlFotoDorso);


                ////GRABO LOS DATOS EN LA BASE
                cli.setIdfotoFrente(urlFotoFrente);
                cli.setIdfotoDorso(urlFotoDorso);

                Persona existePer=service.getPersona(cli.getId());
                if (Objects.nonNull(existePer))/////ES UNA MODIFICACION
                {
                    ////DEBO BORRAR LOS CONCTACTO Y ACTUALIZAR LA IMAGEN y el CLIENTE
                    logger.info("ES UNA ACTUALIZACION DE CLIENTE:: "+cli.getId()+"; DNI: "+cli.getDni());
                    a1 = service.updatePersona(cli);
                    ///DEBO BORRAR TODOS LOS CONTACTOS CARGADOS PARA EL ID
                    serContacto.borrarContactoByIdPersona(cli.getId());
                }
                else////ES UN ALTA DE CLIENTE
                {
                        a1 =  service.createPersona(cli);
                        logger.info("DIO DE ALTAL AL CLIENTE");
                }//////if (Objects.nonNull(existePer))/
                padre.setPersona(a1);
                madre.setPersona(a1);
                abueloPat.setPersona(a1);
                abuelaPat.setPersona(a1);
                abueloMat.setPersona(a1);
                abuelaMat.setPersona(a1);



                logger.info("padre: "+padre.getApellido()+" "+padre.getNombre());
                logger.info("tipoRel: "+padre.getTipoRelacion().getDescripcion());


                pa =  serContacto.createContacto(padre);
                logger.info("DIO DE ALTA EL PADRE");
                ma =  serContacto.createContacto(madre);

                abloPat =  serContacto.createContacto(abueloPat);
                ablaPat =  serContacto.createContacto(abuelaPat);

                abloMat =  serContacto.createContacto(abueloMat);
                ablaMat =  serContacto.createContacto(abuelaMat);

                if ((Objects.isNull(pa)) &&
                        (Objects.isNull(ma)) &&
                        (Objects.isNull(abloPat)) &&
                        (Objects.isNull(ablaPat)) &&
                        (Objects.isNull(abloMat)) &&
                        (Objects.isNull(ablaMat)))
                {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                return ResponseEntity.status(HttpStatus.CREATED).body(a1);



            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        } catch (Exception e) {
            logger.error("DIO ERROR");
            e.printStackTrace();

            /////////////DEBO HACER UN ROLLBACK///////////////
            File f1;
            if (Objects.nonNull(localPath) && Objects.nonNull(nomFileFinal1))
            {
                logger.error("PATH: "+localPath+"/"+nomFileFinal1);
                f1= new File(localPath+"/"+nomFileFinal1);
                f1.delete();
            }

            if (Objects.nonNull(localPath) && Objects.nonNull(nomFileFinal2))
            {
                f1= new File(localPath+"/"+nomFileFinal2);
                f1.delete();
            }





            if (Objects.nonNull(pa))
            {
                serContacto.borrarContacto(pa.getId());
                logger.error("BORRE EL PADRE");
            }


            if (Objects.nonNull(ma))
            {
                serContacto.borrarContacto(ma.getId());
                logger.error("BORRE LA MADRE");
            }

            if (Objects.nonNull(abloPat))
            {
                serContacto.borrarContacto(abloPat.getId());
                logger.error("BORRE EL ABUELO PADRE");
            }

            if (Objects.nonNull(ablaPat))
            {
                serContacto.borrarContacto(ablaPat.getId());
                logger.error("BORRE EL ABUELA PADRE");
            }

            if (Objects.nonNull(abloMat))
            {
                serContacto.borrarContacto(abloMat.getId());
                logger.error("BORRE EL ABUELO MADRE");
            }

            if (Objects.nonNull(ablaMat))
            {
                serContacto.borrarContacto(ablaMat.getId());
                logger.error("BORRE EL ABUELA MADRE");
            }

            if (Objects.nonNull(a1))
            {
                service.borrarPersona(a1.getId());
                logger.error("BORRE EL CLIENTE");
            }



            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);




        }


    }




    @PutMapping(value = "/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable("id") Long id, @RequestBody Persona cli){
        cli.setId(id);
        Persona cliBD =  service.updatePersona(cli);
        if (cliBD == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliBD);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Persona> deletePersona(@PathVariable("id") Long id){
        Persona cliDel = service.deletePersona(id);
        if (cliDel == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliDel);
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
