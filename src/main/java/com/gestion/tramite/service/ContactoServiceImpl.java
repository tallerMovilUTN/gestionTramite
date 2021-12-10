package com.gestion.tramite.service;

import com.gestion.tramite.entidad.Contacto;
import com.gestion.tramite.entidad.Persona;
import com.gestion.tramite.repositorio.ContactoRepositorio;
import com.gestion.tramite.repositorio.PersonaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactoServiceImpl implements ContactoService
{
    @Autowired
    private final ContactoRepositorio repo;


    @Override
    public List<Contacto> listAllContactos()
    {
        return repo.findAll();
    }

    @Override
    public List<Contacto> getContactosByIdPersona(Long idPersona)
    {
        return repo.getContactosByIdPersona(idPersona);
    }

    @Override
    public Contacto createContacto(Contacto a1) {
        a1.setEstado(1);
        a1.setFechaAlta(new Date());
        return repo.save(a1);
    }

    @Override
    public Contacto updateContacto(Contacto a1) {
        Contacto catDB = getContacto(a1.getId());
        if (null == catDB){
            return null;
        }
        //catDB.setDescripcion(a1.getDescripcion());
        catDB.setEstado(a1.getEstado());

        return repo.save(catDB);
    }

    @Override
    public Contacto deleteContacto(Long id) {
        Contacto catDB = getContacto(id);
        if (null == catDB){
            return null;
        }
        catDB.setEstado(0);

        return repo.save(catDB);
    }

    public void borrarContacto(Long id) {
        Contacto contacto = getContacto(id);
        repo.delete(contacto);

    }

    @Override
    public void borrarContactoByIdPersona(Long idPersona)
    {

        List<Contacto> contactos = this.getContactosByIdPersona(idPersona);
        for (Contacto contact:contactos)
        {
            repo.delete(contact);
        }
    }



    @Override
    public Contacto getContacto(Long id) {
        return repo.findById(id).orElse(null);
    }

}
