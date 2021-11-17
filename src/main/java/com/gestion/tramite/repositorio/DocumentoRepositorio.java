package com.gestion.tramite.repositorio;

import com.gestion.tramite.entidad.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepositorio extends JpaRepository<Documento, Integer> {

}
