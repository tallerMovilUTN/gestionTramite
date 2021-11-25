package com.gestion.tramite.repositorio;


import com.gestion.tramite.entidad.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepositorio extends JpaRepository<Persona, Long> {


}
