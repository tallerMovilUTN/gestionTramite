package com.gestion.tramite.controller;

import com.gestion.tramite.entidad.Contacto;
import com.gestion.tramite.entidad.Persona;
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

@CrossOrigin( origins = "*" , methods = { RequestMethod. GET , RequestMethod. POST,RequestMethod.PUT,RequestMethod.DELETE })
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










    @GetMapping(value = "/{idPersona}")
    public ResponseEntity<List<Contacto>> getContactos(@PathVariable("idPersona") Long idPersona)
    {
            logger.info("ESTOY EN getContacto-"+idPersona);
            List<Contacto> result = service.getContactosByIdPersona(idPersona);
            if (result.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(result);
    }










    @PostMapping
    public ResponseEntity<Contacto> createContacto(@RequestBody Contacto cli)
    {
        Contacto a1 =  service.createContacto(cli);
        return ResponseEntity.status(HttpStatus.CREATED).body(a1);
    }



    @PutMapping(value = "/{id}")
    public ResponseEntity<Contacto> updateContacto(@PathVariable("id") Long id, @RequestBody Contacto cli){
        cli.setId(id);
        Contacto cliBD =  service.updateContacto(cli);
        if (cliBD == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliBD);
    }


    @PostMapping(value = "/actualizarContacto")
    public ResponseEntity<Contacto> actualizarContacto(@RequestBody Contacto cli)
    {
        Contacto a1 = service.getContacto(cli.getId());
        if (a1 == null)
        {
            a1 =  service.createContacto(cli);
            return ResponseEntity.status(HttpStatus.CREATED).body(a1);
        }
        else
        {
            a1 =  service.updateContacto(cli);
            return ResponseEntity.ok(a1);
        }
        //Contacto a1 =  service.createContacto(cli);

    }



    @PostMapping(value = "/borrarContacto")
    public ResponseEntity<Contacto> deleteContacto(@RequestBody Contacto cli)
    {
        logger.info("ESTOY POR BORRAR EL CONTACTO: "+cli.getId());
        service.borrarContacto(cli.getId());
        return ResponseEntity.ok(cli);
    }


    @PostMapping(value = "/borrarContactos")
    public ResponseEntity<Persona> deleteContacto(@RequestBody Persona cli)
    {
        logger.info("ESTOY POR BORRAR EL CONTACTO: "+cli.getId());
        service.borrarContactosByIdPersona(cli.getId());
        return ResponseEntity.ok(cli);
    }



    /**
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Contacto> deleteContacto(@PathVariable("id") Long id){
        Contacto cliDel = service.deleteContacto(id);
        if (cliDel == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliDel);
    }**/


    @GetMapping(value = "/getContactosByIdPersona/{idPersona}/{idTipoRelacion}")
    public ResponseEntity<Contacto> getContactosByIdPersona(@PathVariable("idPersona") Long idPersona,@PathVariable("idTipoRelacion") Long idTipoRelacion)
    {
        logger.info("ESTOY EN getContacto-"+idPersona);
        Contacto result = service.getContactosByIdPersonaAndTipoRelacion(idPersona,idTipoRelacion);
        if (result == null)
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

}
