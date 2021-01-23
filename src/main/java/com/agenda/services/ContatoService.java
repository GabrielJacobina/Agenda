package com.agenda.services;

import com.agenda.models.Contato;
import com.agenda.repositorys.ContatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContatoService {

    private final ContatoRepository contatoRepository;


    public Page<Contato> listAll(Pageable pageable) {
        return contatoRepository.findAll(pageable);
    }
}
