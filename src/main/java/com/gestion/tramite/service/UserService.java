package com.gestion.tramite.service;


import com.gestion.tramite.entidad.Contacto;
import com.gestion.tramite.entidad.User;

import java.util.List;

public interface UserService {

    public User getUser(String user);
    public User createUser(User a1);
    public User updateUser(User a1);
    public User deleteUser(User a1);

}
