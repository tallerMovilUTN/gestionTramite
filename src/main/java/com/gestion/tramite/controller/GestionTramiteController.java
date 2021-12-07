package com.gestion.tramite.controller;


import com.gestion.tramite.entidad.Contacto;
import com.gestion.tramite.entidad.GestionTramite;
import com.gestion.tramite.service.ContactoService;
import com.gestion.tramite.service.GestionTramiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin( origins = "*" , methods = { RequestMethod. GET , RequestMethod. POST,RequestMethod.PUT })
@RestController
@RequestMapping(value = "/gestionTramites")
public class GestionTramiteController
{
    @Autowired
    GestionTramiteService service;
    Logger logger = LoggerFactory.getLogger(GestionTramiteController.class);

    @GetMapping
    public ResponseEntity<List<GestionTramite>> listGestionTramite()
    {
        List<GestionTramite> gest = new ArrayList<>();
        gest = service.listAllGestionTramite();
        if (gest.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(gest);
    }



    @GetMapping(value = "/{idGestionTramite}")
    public ResponseEntity<GestionTramite> getGestionTramite(@PathVariable("idGestionTramite") Long idGestionTramite)
    {
        logger.info("ESTOY EN OBTENER-GestionTramite-"+idGestionTramite);
        GestionTramite result = service.getGestionTramite(idGestionTramite);
        if (null==result){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }


    @PostMapping
    public ResponseEntity<GestionTramite> createGestionTramite(@RequestBody GestionTramite a1)
    {
        GestionTramite tra =  service.createGestionTramite(a1);
        return ResponseEntity.status(HttpStatus.CREATED).body(tra);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<GestionTramite> updateGestionTramite(@PathVariable("id") Long id, @RequestBody GestionTramite ge){
        ge.setId(id);
        GestionTramite cliBD =  service.updateGestionTramite(ge);
        if (cliBD == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliBD);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<GestionTramite> deleteGestionTramite(@PathVariable("id") Long id){
        GestionTramite cliDel = service.deleteGestionTramite(id);
        if (cliDel == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliDel);
    }


}
