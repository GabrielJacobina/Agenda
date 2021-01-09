package com.agenda.services;

import com.agenda.models.Contato;
import com.agenda.repositorys.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;


    public Page<Contato> listAll(Pageable pageable) {
        return contatoRepository.findAll(pageable);
    }
}
