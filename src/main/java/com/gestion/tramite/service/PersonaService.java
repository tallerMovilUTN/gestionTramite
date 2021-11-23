package com.gestion.tramite.service;



import com.gestion.tramite.entidad.Persona;
import org.springframework.core.io.Resource;


import java.util.List;

public interface PersonaService {

    public List<Persona> listAllCliente();
    public Persona getPersona(Integer id);
    public Persona createPersona(Persona a1);
    public Persona updatePersona(Persona a1);
    public Persona deletePersona(Integer id);
    public void borrarPersona(Integer id);
    public Resource load(String filename,String carpeta);
}
