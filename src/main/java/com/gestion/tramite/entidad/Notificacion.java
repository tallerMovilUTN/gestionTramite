package com.gestion.tramite.entidad;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notificaciones")
@Data
public class Notificacion {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private Integer tiempo;
    private String mensaje;
    private Date fechaAlta;
    private Integer estado;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGestionTramite")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private GestionTramite gestionTramite;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoNotificacion")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private TipoNotificacion tipoNotificacion;

}
