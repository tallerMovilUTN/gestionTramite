package com.gestion.tramite.service;



import com.gestion.tramite.entidad.Persona;
import org.springframework.core.io.Resource;


import java.util.List;

public interface PersonaService {

    public List<Persona> listAllCliente();
    public Persona getPersona(Long id);
    public Persona createPersona(Persona a1);
    public Persona updatePersona(Persona a1);
    public Persona deletePersona(Long id);
    public void borrarPersona(Long id);
    public Resource load(String filename,String carpeta);
    public Persona getPersonaPorDni (int dni);
}
