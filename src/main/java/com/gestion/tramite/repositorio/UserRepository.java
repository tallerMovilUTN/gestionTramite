package com.gestion.tramite.repositorio;

import com.gestion.tramite.entidad.Documento;
import com.gestion.tramite.entidad.Persona;
import com.gestion.tramite.entidad.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUser(String user);

}
