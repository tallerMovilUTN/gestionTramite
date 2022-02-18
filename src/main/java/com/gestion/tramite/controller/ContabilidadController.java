package com.gestion.tramite.controller;


import com.gestion.tramite.entidad.Contabilidad;
import com.gestion.tramite.entidad.Documento;
import com.gestion.tramite.service.ContabilidadService;
import com.gestion.tramite.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin( origins = "*" , methods = { RequestMethod. GET , RequestMethod. POST , RequestMethod.DELETE ,RequestMethod.PUT })
@RestController
@RequestMapping(value = "/contabilidad")
public class ContabilidadController
{
    @Autowired
    private ContabilidadService service;

    @GetMapping
    public ResponseEntity<List<Contabilidad>> listAllContabilidad()
    {
        List<Contabilidad> a1 = new ArrayList<>();
        a1 = service.listAllContabilidad();
        if (a1.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(a1);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Contabilidad> getContabilidad(@PathVariable("id") Long id)
    {
        Contabilidad cat =  service.getContabilidad(id);
        if (null==cat){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cat);
    }



    @PostMapping
    public ResponseEntity<Contabilidad> createContabilidad(@RequestBody Contabilidad cli)
    {
        Contabilidad a1 =  service.createContabilidad(cli);
        return ResponseEntity.status(HttpStatus.CREATED).body(a1);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Contabilidad> updateContabilidad(@PathVariable("id") Long id, @RequestBody Contabilidad cli){

        System.out.println("estoy en updateContabilidad");
        System.out.println("estoy en updateContabilidad id"+id);
        cli.setId(id);
        Contabilidad cliBD =  service.updateContabilidad(cli);
        if (cliBD == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliBD);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Contabilidad>  deleteContabilidad(@PathVariable("id") Long id){

        System.out.println("estoy en deleteContabilidad");

        Contabilidad cont=service.deleteContabilidad(id);

        System.out.println("estoy en deleteContabilidad 2");
        if (cont == null)
        {
            return ResponseEntity.notFound().build();
        }

        System.out.println("estoy en deleteContabilidad 3");

        return ResponseEntity.ok(cont);
    }




}
