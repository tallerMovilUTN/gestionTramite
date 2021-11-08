package com.gestion.tramite.repositorio;


import com.gestion.tramite.entidad.Contacto;
import com.gestion.tramite.entidad.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactoRepositorio extends JpaRepository<Contacto, Integer> {


}
