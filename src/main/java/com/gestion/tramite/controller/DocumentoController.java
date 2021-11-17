package com.gestion.tramite.controller;

import com.gestion.tramite.entidad.Documento;
import com.gestion.tramite.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin( origins = "*" , methods = { RequestMethod. GET , RequestMethod. POST , RequestMethod.DELETE ,RequestMethod.PUT })
@RestController
@RequestMapping(value = "/documentos")
public class DocumentoController
{
    @Autowired
    private DocumentoService service;


    @GetMapping
    public ResponseEntity<List<Documento>> listTDocumento()
    {
        List<Documento> cat = new ArrayList<>();
        cat = service.listAllDocumento();
        if (cat.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cat);
    }







    @GetMapping(value = "/{id}")
    public ResponseEntity<Documento> getDocumento(@PathVariable("id") Integer id)
    {
        Documento cat =  service.getDocumento(id);
        if (null==cat){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cat);
    }

    @PostMapping
    public ResponseEntity<Documento> createDocumento(@RequestBody Documento cli)
    {
        Documento a1 =  service.createDocumento(cli);
        return ResponseEntity.status(HttpStatus.CREATED).body(a1);
    }



    @PutMapping(value = "/{id}")
    public ResponseEntity<Documento> updateDocumento(@PathVariable("id") Integer id, @RequestBody Documento cli){

        System.out.println("estoy en updateTDocumento");
        System.out.println("estoy en updateDocumento id"+id);
        cli.setId(id);
        Documento cliBD =  service.updateDocumento(cli);
        if (cliBD == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliBD);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Documento>  deleteDocumento(@PathVariable("id") Integer id){

        System.out.println("estoy en deleteDocumento");

        Documento cliDel=service.deleteDocumento(id);

        System.out.println("estoy en deleteDocumento 2");
        if (cliDel == null)
        {
            return ResponseEntity.notFound().build();
        }

        System.out.println("estoy en deleteDocumento 3");

        return ResponseEntity.ok(cliDel);
    }
}
