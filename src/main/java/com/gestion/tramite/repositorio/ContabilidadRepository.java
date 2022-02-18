package com.gestion.tramite.repositorio;

import com.gestion.tramite.entidad.Contabilidad;
import com.gestion.tramite.entidad.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ContabilidadRepository  extends JpaRepository<Contabilidad, Long> {



}
