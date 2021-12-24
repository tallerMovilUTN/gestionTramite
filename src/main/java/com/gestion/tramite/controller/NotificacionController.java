package com.gestion.tramite.controller;



import com.gestion.tramite.entidad.Archivo;
import com.gestion.tramite.entidad.Notificacion;
import com.gestion.tramite.service.NotificacionService;
import com.gestion.tramite.service.TipoNotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin( origins = "*" , methods = { RequestMethod. GET , RequestMethod. POST , RequestMethod.DELETE ,RequestMethod.PUT })
@RestController
@RequestMapping(value = "/notificacion")
public class NotificacionController {


    @Autowired
    private NotificacionService service;


    @GetMapping
    public ResponseEntity<List<Notificacion>> listNotificacion()
    {
        List<Notificacion> cat = new ArrayList<>();
        cat = service.listAllNotificacion();
        if (cat.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cat);
    }







    @GetMapping(value = "/{id}")
    public ResponseEntity<Notificacion> getNotificacion(@PathVariable("id") Long id)
    {
        Notificacion cat =  service.getNotificacion(id);
        if (null==cat){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cat);
    }

    @PostMapping
    public ResponseEntity<Notificacion> createNotificacion(@RequestBody Notificacion cli)
    {
        Notificacion a1 =  service.createNotificacion(cli);
        return ResponseEntity.status(HttpStatus.CREATED).body(a1);
    }



    @PutMapping(value = "/{id}")
    public ResponseEntity<Notificacion> updateNotificacion(@PathVariable("id") Long id, @RequestBody Notificacion cli){

        System.out.println("estoy en updateNotificacion id"+id);
        cli.setId(id);
        Notificacion cliBD =  service.updateNotificacion(cli);
        if (cliBD == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliBD);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Notificacion>  deleteNotificacion(@PathVariable("id") Long id){

        System.out.println("estoy en deleteNotificacion");

        Notificacion cliDel=service.deleteNotificacion(id);

        if (cliDel == null)
        {
            return ResponseEntity.notFound().build();
        }

        System.out.println("estoy en deleteNotificacion 3");

        return ResponseEntity.ok(cliDel);
    }


    @GetMapping("/notificacionesTiempo")
    public ResponseEntity<List<Notificacion>> obtenerNotificacionesTiempo()
    {

        List<Notificacion> listNotificacion = service.obtenerNotificacionesTiempo();
        /**if (listFile.size() > 0)
         {
         ////OBTENGO LA LISTA DE URL Y ARCHIVOS DE LA CARPETA
         Integer dni= listFile.get(0).getDni();
         logger.info("dni: "+dni);

         List<FileModel> fileInfos = fileService.loadAll(dni.toString()).map(path -> {
         String filename = path.getFileName().toString();
         logger.info("filename: "+filename);
         String url = MvcUriComponentsBuilder.fromMethodName(FileController.class, "getFile",
         path.getFileName().toString()).build().toString();
         return new FileModel(filename, url);
         }).collect(Collectors.toList());

         logger.info("listFile.size(): "+listFile.size());
         logger.info("fileInfos.size(): "+fileInfos.size());

         int index =0;
         for (Archivo arch:listFile)
         {
         for (FileModel info:fileInfos)
         {
         logger.info("COMPARO: "+arch.getNombre());
         logger.info("COMPARO: "+info.getName());
         if (arch.getNombre().equals(info.getName()))
         {
         listFile.get(index).setUrl(info.getUrl());
         break;
         }
         }
         index++;
         }
         }**/
        return ResponseEntity.status(HttpStatus.OK).body(listNotificacion);
    }

}
