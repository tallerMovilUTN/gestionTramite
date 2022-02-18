package com.gestion.tramite.repositorio;

import com.gestion.tramite.entidad.Contabilidad;
import com.gestion.tramite.entidad.Emails;
import com.gestion.tramite.entidad.Movimientos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface  MovimientosRepository extends JpaRepository<Movimientos, Long> {

    @Query("select m from Movimientos m where m.contabilidad.id = :idContabilidad order by m.id desc")
    public List<Movimientos> listAllMovimientosByIdContabilidad(@Param("idContabilidad") Long idContabilidad);


}
