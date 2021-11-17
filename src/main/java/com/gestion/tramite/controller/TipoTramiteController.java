package com.gestion.tramite.controller;


import com.gestion.tramite.entidad.TipoTramite;
import com.gestion.tramite.service.TipoTramiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin( origins = "*" , methods = { RequestMethod. GET , RequestMethod. POST , RequestMethod.DELETE ,RequestMethod.PUT })
@RestController
@RequestMapping(value = "/tramites")
public class TipoTramiteController
{
    @Autowired
    private TipoTramiteService service;


    @GetMapping
    public ResponseEntity<List<TipoTramite>> listTipoTramite()
    {
        List<TipoTramite> cat = new ArrayList<>();
        cat = service.listAllTipoTramite();
        if (cat.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cat);
    }







    @GetMapping(value = "/{id}")
    public ResponseEntity<TipoTramite> getTipoTramite(@PathVariable("id") Integer id)
    {
        TipoTramite cat =  service.getTipoTramite(id);
        if (null==cat){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cat);
    }

    @PostMapping
    public ResponseEntity<TipoTramite> createTipoTramite(@RequestBody TipoTramite cli)
    {
        TipoTramite a1 =  service.createTipoTramite(cli);
        return ResponseEntity.status(HttpStatus.CREATED).body(a1);
    }



    @PutMapping(value = "/{id}")
    public ResponseEntity<TipoTramite> updateTipoTramite(@PathVariable("id") Integer id, @RequestBody TipoTramite cli){

        System.out.println("estoy en updateTipoTramite");
        System.out.println("estoy en updateTipoTramite id"+id);
        cli.setId(id);
        TipoTramite cliBD =  service.updateTipoTramite(cli);
        if (cliBD == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliBD);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TipoTramite>  deleteTipoTramite(@PathVariable("id") Integer id){

        System.out.println("estoy en deleteTipoTramite");

        TipoTramite cliDel=service.deleteTipoTramite(id);

        System.out.println("estoy en deleteTipoTramite 2");
        if (cliDel == null)
        {
           return ResponseEntity.notFound().build();
        }

        System.out.println("estoy en deleteTipoTramite 3");

       return ResponseEntity.ok(cliDel);
    }
}
