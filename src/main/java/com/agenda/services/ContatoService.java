package com.agenda.services;

import com.agenda.models.Contato;
import com.agenda.repositorys.ContatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ContatoService {

    private final ContatoRepository contatoRepository;


    public Page<Contato> listAll(Pageable pageable) {
        return contatoRepository.findAll(pageable);
    }

    public Contato findById(long id) {
        return contatoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Request"));
    }

    public Contato save(Contato contato) {
        return contatoRepository.save(contato);
    }

    public void delete(long id) {
        contatoRepository.delete(findById(id));
    }

    public void replace(Contato contato) {
        Contato savedUsuario = findById(contato.getId());
        contatoRepository.save(contato);
    }
}
