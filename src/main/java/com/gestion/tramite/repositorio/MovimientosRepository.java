package com.gestion.tramite.repositorio;

import com.gestion.tramite.entidad.Contabilidad;
import com.gestion.tramite.entidad.Emails;
import com.gestion.tramite.entidad.Movimientos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface  MovimientosRepository extends JpaRepository<Movimientos, Long> {

    @Query("select m from Movimientos m where m.contabilidad.id = :idContabilidad order by m.nroCuota asc")
    public List<Movimientos> listAllMovimientosByIdContabilidad(@Param("idContabilidad") Long idContabilidad);

//@Query(value ="SELECT id, tiempo, idGestionTramite, mensaje, fechaAlta, idTipoNotificacion, estado FROM gestionciudadania.notificaciones where date(now())=DATE_ADD(fechaAlta, INTERVAL tiempo DAY);" , nativeQuery=true)
    @Query(value = "SELECT a.* FROM movimientos a inner join contabilidad b on a.idContabilidad=b.id where b.estado=1 and a.estado in (0,2) and DATEDIFF(fechaVencimiento, date(now()))<7 order by idContabilidad,nroCuota;",nativeQuery = true)
    public List<Movimientos> listAllCuotasPorVencer();

//   SELECT *,DATEDIFF(fechaVencimiento, date(now())) FROM movimientos where estado in (0,2) and DATEDIFF(fechaVencimiento, date(now()))<7 order by idContabilidad,nroCuota
}
