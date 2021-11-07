package com.gestion.tramite.controller;


import com.gestion.tramite.service.ConsultasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@CrossOrigin( origins = "*" , methods = { RequestMethod. GET , RequestMethod. POST })
@RestController
@RequestMapping(value = "/consultas")
public class ConsultasController {


    @Autowired
    ConsultasService service;

    @GetMapping(value = "/{cp}")
    public ResponseEntity<Object> getDatosCP(@PathVariable("cp") Integer cp)
    {
        Object cat ;
        cat = service.listAll(cp);
        if (Objects.isNull(cat))
        {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cat);
    }
}
