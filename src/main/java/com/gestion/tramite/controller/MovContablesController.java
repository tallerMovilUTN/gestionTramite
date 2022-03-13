package com.gestion.tramite.controller;


import com.gestion.tramite.entidad.Contabilidad;
import com.gestion.tramite.entidad.FiltroMovDTO;
import com.gestion.tramite.entidad.MovContableDTO;
import com.gestion.tramite.service.ContabilidadService;
import com.gestion.tramite.service.MovContableDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin( origins = "*" , methods = { RequestMethod. GET , RequestMethod. POST , RequestMethod.DELETE ,RequestMethod.PUT })
@RestController
@RequestMapping(value = "/movContables")
public class MovContablesController
{

    @Autowired
    private MovContableDTOService service;

    @PostMapping
    public ResponseEntity<List<MovContableDTO>> listarMovContables(@RequestBody FiltroMovDTO filtro)
    {
            List<MovContableDTO> a1 = new ArrayList<>();
            a1 = service.listarMovContables(filtro);
            if (a1.isEmpty())
            {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(a1);
    }

}
