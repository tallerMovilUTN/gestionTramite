package com.gestion.tramite.service;

import com.gestion.tramite.entidad.Persona;
import com.gestion.tramite.repositorio.PersonaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService
{
    private final PersonaRepositorio repo;


    @Override
    public List<Persona> listAllCliente() {
        return repo.findAll();
    }

    @Override
    public Persona getPersona(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Persona createPersona(Persona a1) {
        a1.setEstado(1);
        a1.setFechaAlta(new Date());
        return repo.save(a1);
    }

    @Override
    public Persona updatePersona(Persona a1) {
        Persona catDB = getPersona(a1.getId());
        if (null == catDB){
            return null;
        }
        //catDB.setDescripcion(a1.getDescripcion());
        catDB.setEstado(a1.getEstado());

        return repo.save(catDB);
    }

    @Override
    public Persona deletePersona(Integer id) {
        Persona catDB = getPersona(id);
        if (null == catDB){
            return null;
        }
        catDB.setEstado(0);

        return repo.save(catDB);
    }




    @Override
    public void borrarPersona(Integer id) {
        Persona pe = getPersona(id);

        repo.delete(pe);
        //return repo.save(catDB);
    }
}
