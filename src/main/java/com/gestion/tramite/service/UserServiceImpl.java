package com.gestion.tramite.service;

import com.gestion.tramite.entidad.Contacto;
import com.gestion.tramite.entidad.User;
import com.gestion.tramite.repositorio.ContactoRepositorio;
import com.gestion.tramite.repositorio.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    @Override
    public User getUser(String user) {
        return repo.findByUser(user);
    }

    @Override
    public User createUser(User a1) {
        a1.setEstado(1);
        return repo.save(a1);
    }

    @Override
    public User updateUser(User a1) {
        User usrDB = repo.findByUser(a1.getUser());
        if (null == usrDB){
            return null;
        }
        usrDB.setEstado(a1.getEstado());
        usrDB.setToken(a1.getToken());
        return repo.save(usrDB);
    }

    @Override
    public User deleteUser(User a1) {
        User usrDB = repo.findByUser(a1.getUser());
        if (null == usrDB){
            return null;
        }
        usrDB.setEstado(0);
        return repo.save(usrDB);
    }




}
