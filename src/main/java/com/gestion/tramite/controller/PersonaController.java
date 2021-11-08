package com.gestion.tramite.controller;

import com.gestion.tramite.entidad.Persona;
import com.gestion.tramite.service.PersonaService;
import com.gestion.tramite.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@CrossOrigin( origins = "*" , methods = { RequestMethod. GET , RequestMethod. POST })
@RestController
@RequestMapping(value = "/personas")
public class PersonaController
{
    @Autowired
    PersonaService service;
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
    public ResponseEntity<Persona> getPersona(@PathVariable("id") Integer id)
    {
        logger.info("ESTOY EN getPersona-"+id);
        Persona cat =  service.getPersona(id);
        if (null==cat){
            return ResponseEntity.notFound().build();
        }
        logger.info("ESTOY EN getCliente-"+cat.getApellido());

        return ResponseEntity.ok(cat);
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



        String localPath="C:/IntelliJ/gestionTramiteDocumentos";
        //String nameFileFinal= fileName+getFileExtension(file.getOriginalFilename());
        String extension = getFileExtension(file1.getOriginalFilename());
        logger.info("EXTENSION ARCHIVO:: "+extension);
        String fileName1 = cli.getDni()+"-fotoFrente"+extension;
        String fileName2 = cli.getDni()+"-fotoDorso"+extension;
        cli.setIdfotoDorso(localPath+"/"+fileName1);
        cli.setIdfotoFrente(localPath+"/"+fileName2);
        Persona a1 =  service.createPersona(cli);

        FileUtils.upload(file1, localPath, fileName1);
        FileUtils.upload(file2, localPath, fileName2);
        return ResponseEntity.status(HttpStatus.CREATED).body(a1);
    }





    @PutMapping(value = "/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable("id") Integer id, @RequestBody Persona cli){
        cli.setId(id);
        Persona cliBD =  service.updatePersona(cli);
        if (cliBD == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliBD);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Persona> deletePersona(@PathVariable("id") Integer id){
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
}
