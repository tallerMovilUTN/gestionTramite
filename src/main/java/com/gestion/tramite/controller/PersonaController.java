package com.gestion.tramite.controller;

import com.gestion.tramite.entidad.Persona;
import com.gestion.tramite.service.PersonaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
}
