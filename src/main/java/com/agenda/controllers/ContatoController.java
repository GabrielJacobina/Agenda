package com.agenda.controllers;

import com.agenda.dto.ContatoDTO;
import com.agenda.models.Contato;
import com.agenda.services.ContatoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class ContatoController {

    private final ContatoService contatoService;

    @GetMapping("/contatos")
    public ResponseEntity<Page<Contato>> getAllContatos(Pageable pageable){
        return new ResponseEntity<>(contatoService.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/contatos/{id}")
    public ResponseEntity<Contato> getOneContato(@PathVariable(value = "id") long id){
        return new ResponseEntity<>(contatoService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/contatos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Contato> createContato(@Valid @RequestBody ContatoDTO contatoDTO){
        return new ResponseEntity<>(contatoService.save(contatoDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/contatos/{id}")
    public ResponseEntity<Void> deleteContato(@PathVariable(value = "id") long id){
        contatoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/contatos/{id}")
    public ResponseEntity<Contato> replaceContato(@Valid @RequestBody ContatoDTO contatoDTO) {
        contatoService.replace(contatoDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
