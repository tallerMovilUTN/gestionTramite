package com.gestion.tramite.controller;

import com.gestion.tramite.entidad.TipoNotificacion;
import com.gestion.tramite.entidad.TipoTramite;
import com.gestion.tramite.service.TipoNotificacionService;
import com.gestion.tramite.service.TipoTramiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin( origins = "*" , methods = { RequestMethod. GET , RequestMethod. POST , RequestMethod.DELETE ,RequestMethod.PUT })
@RestController
@RequestMapping(value = "/tipoNotificacion")
public class TipoNotificacionController {

    @Autowired
    private TipoNotificacionService service;


    @GetMapping
    public ResponseEntity<List<TipoNotificacion>> listTipoNotificacion()
    {
        List<TipoNotificacion> cat = new ArrayList<>();
        cat = service.listAllTipoNotificacion();
        if (cat.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cat);
    }







    @GetMapping(value = "/{id}")
    public ResponseEntity<TipoNotificacion> getTipoNotificacion(@PathVariable("id") Long id)
    {
        TipoNotificacion cat =  service.getTipoNotificacion(id);
        if (null==cat){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cat);
    }

    @PostMapping
    public ResponseEntity<TipoNotificacion> createTipoNotificacion(@RequestBody TipoNotificacion cli)
    {
        TipoNotificacion a1 =  service.createTipoNotificacion(cli);
        return ResponseEntity.status(HttpStatus.CREATED).body(a1);
    }



    @PutMapping(value = "/{id}")
    public ResponseEntity<TipoNotificacion> updateTipoNotificacion(@PathVariable("id") Long id, @RequestBody TipoNotificacion cli){

        System.out.println("estoy en updateTipoNotificacion id"+id);
        cli.setId(id);
        TipoNotificacion cliBD =  service.updateTipoNotificacion(cli);
        if (cliBD == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliBD);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TipoNotificacion>  deleteTipoNotificacion(@PathVariable("id") Long id){

        System.out.println("estoy en deleteTipoNotificacion");

        TipoNotificacion cliDel=service.deleteTipoNotificacion(id);

        if (cliDel == null)
        {
            return ResponseEntity.notFound().build();
        }

        System.out.println("estoy en deleteTipoNotificacion 3");

        return ResponseEntity.ok(cliDel);
    }

}
