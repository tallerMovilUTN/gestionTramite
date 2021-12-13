package com.gestion.tramite.entidad;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tiponotificacion")
@Data
public class TipoNotificacion {

        @Id
        @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
        @GenericGenerator(name = "native",strategy = "native")
        private Long id;
        private String descripcion;
        private String color;
        private Integer estado;

}
