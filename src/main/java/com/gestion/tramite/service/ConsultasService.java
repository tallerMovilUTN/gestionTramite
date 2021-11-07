package com.gestion.tramite.service;


import com.gestion.tramite.repositorio.ConsultasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsultasService {

    private final ConsultasRepository repo;


    public Object listAll(int cp) {
        return repo.getAll(cp);
    }

}
