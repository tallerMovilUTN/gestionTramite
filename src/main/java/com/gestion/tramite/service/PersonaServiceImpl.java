package com.gestion.tramite.service;

import com.gestion.tramite.controller.PersonaController;
import com.gestion.tramite.entidad.Persona;
import com.gestion.tramite.repositorio.PersonaRepositorio;
import lombok.RequiredArgsConstructor;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService
{

    Logger logger = LoggerFactory.getLogger(PersonaServiceImpl.class);


    @Value("${rutaDoc}")
    private String rutaDoc;


    private final PersonaRepositorio repo;


    @Override
    public List<Persona> listAllCliente() {
        return repo.getPersonasActivas();
    }

    @Override
    public Persona getPersona(Long id) {
        return repo.findById(id).orElse(null);
    }


    @Override
    public Persona getPersonaPorDni(int dni) {
        return repo.getPersonasActivasByDNI(dni);
    }

    @Override
    public Persona createPersona(Persona a1) {
        //if (a1.getId() > 0)///VERIFICO SI EXISTE
        a1.setEstado(1);
        a1.setFechaAlta(new Date());
        return repo.save(a1);
    }

    @Override
    public Persona updatePersona(Persona a1) {
        return repo.save(a1);
    }

    @Override
    public Persona deletePersona(Long id) {
        System.out.println("estoy en back deletePersona");

        Persona catDB = getPersona(id);
        if (null == catDB){
            return null;
        }
        System.out.println("estoy en back deletePersona 2");
        //catDB.setEstado(0);

        //return repo.save(catDB);

        repo.delete(catDB);
        return catDB;
    }






    @Override
    public void borrarPersona(Long id) {
        Persona pe = getPersona(id);

        repo.delete(pe);
        //return repo.save(catDB);
    }



    @Override
    public Resource load(String filename,String carpeta) {
        try {
            //String localPath="C:/IntelliJ/gestionTramiteDocumentos";
            //Path file = Paths.get(ruta + filename);
            logger.info("ESTOY EN LOAD===> "+rutaDoc+carpeta+"/"+filename);
            Path file = Paths.get(rutaDoc+carpeta+"/"+filename);

            logger.info("ESTOY EN LOAD===> "+file.toUri());

            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {

                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
