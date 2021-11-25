package com.gestion.tramite.repositorio;


import com.gestion.tramite.entidad.GestionTramite;
import com.gestion.tramite.entidad.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GestionTramiteRepositorio extends JpaRepository<GestionTramite, Integer> {


}
