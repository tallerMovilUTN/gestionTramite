package com.gestion.tramite.service;

import com.gestion.tramite.entidad.Documento;
import com.gestion.tramite.repositorio.DocumentoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentoServiceImpl implements DocumentoService
{
    @Autowired
    private final DocumentoRepositorio repo;


    @Override
    public List<Documento> listAllDocumento() {
        return repo.findAll();
    }

    @Override
    public Documento getDocumento(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Documento createDocumento(Documento a1) {
        a1.setEstado(1);
        return repo.save(a1);
    }

    @Override
    public Documento updateDocumento(Documento a1) {

        System.out.println("updateDocumento en serviceImpl 1");
        Documento catDB = getDocumento(a1.getId());
        if (null == catDB){
            return null;
        }
        //catDB.setDescripcion(a1.getDescripcion());
        System.out.println("updateDocumento en serviceImpl 2");
        catDB.setEstado(1);
        System.out.println("updateDocumento en serviceImpl 3");
        catDB.setNombre(a1.getNombre());
        System.out.println("updateDocumento en serviceImpl 4");
        catDB.setDescripcion(a1.getDescripcion());
        System.out.println("updateDocumento en serviceImpl 5");


        return repo.save(catDB);
    }

    @Override
    public Documento deleteDocumento(Integer id) {

        System.out.println("deleteDocumento en serviceImpl 1");
        Documento catDB = getDocumento(id);

        System.out.println("deleteDocumento en serviceImpl 2");
        if (null == catDB){
            return null;
        }
        System.out.println("deleteDocumento en serviceImpl 3");
        //catDB.setEstado(0);
        System.out.println("deleteDocumento en serviceImpl 4");
        repo.delete(catDB);
        System.out.println("deleteDocumento en serviceImpl 5");

        //return repo.save(catDB);
        return catDB;

    }
}
