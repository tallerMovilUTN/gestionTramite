package com.gestion.tramite.controller;

import com.gestion.tramite.entidad.Contacto;
import com.gestion.tramite.service.ContactoService;
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

@CrossOrigin( origins = "*" , methods = { RequestMethod. GET , RequestMethod. POST })
@RestController
@RequestMapping(value = "/contactos")
public class ContactoController
{
    @Autowired
    ContactoService service;
    Logger logger = LoggerFactory.getLogger(ContactoController.class);


    @GetMapping
    public ResponseEntity<List<Contacto>> listContactos()
    {
        List<Contacto> cat = new ArrayList<>();
        cat = service.listAllContactos();
        if (cat.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cat);
    }



    @GetMapping(value = "/{id}")
    public ResponseEntity<Contacto> getPersona(@PathVariable("id") Integer id)
    {
        logger.info("ESTOY EN getContacto-"+id);
        Contacto cat =  service.getContacto(id);
        if (null==cat){
            return ResponseEntity.notFound().build();
        }
        logger.info("ESTOY EN getContacto-"+cat.getApellido());

        return ResponseEntity.ok(cat);
    }




    @PostMapping
    public ResponseEntity<Contacto> createContacto(@RequestBody Contacto cli)
    {
        Contacto a1 =  service.createContacto(cli);
        return ResponseEntity.status(HttpStatus.CREATED).body(a1);
    }



    @PutMapping(value = "/{id}")
    public ResponseEntity<Contacto> updatePersona(@PathVariable("id") Integer id, @RequestBody Contacto cli){
        cli.setId(id);
        Contacto cliBD =  service.updateContacto(cli);
        if (cliBD == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliBD);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Contacto> deleteContacto(@PathVariable("id") Integer id){
        Contacto cliDel = service.deleteContacto(id);
        if (cliDel == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliDel);
    }



}
