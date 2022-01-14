package com.gestion.tramite.entidad;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "emails")
@Data
public class Emails {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private String para;
    private String asunto;
    private String mensaje;
    private Date fechaEnvio;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGestionTramite")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private GestionTramite gestionTramite;

}
