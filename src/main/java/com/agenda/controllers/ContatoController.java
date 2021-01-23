package com.agenda.controllers;

import com.agenda.models.Contato;
import com.agenda.repositorys.ContatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class ContatoController {

    private final ContatoRepository contatoRepository;

    @GetMapping("/contatos")
    public ResponseEntity<List<Contato>> getAllContatos(){
        List<Contato> contatos = contatoRepository.findAll();
        if (contatos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contatos, HttpStatus.OK);
    }

    @GetMapping("/contatos/{id}")
    public ResponseEntity<Contato> getOneContato(@PathVariable(value = "id") long id){
        Optional<Contato> contatoO = contatoRepository.findById(id);
        if (contatoO.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contatoO.get(), HttpStatus.OK);
    }

    @PostMapping("/contatos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Contato> createContato(@Valid @RequestBody Contato contato){
        return new ResponseEntity<>(contatoRepository.save(contato), HttpStatus.CREATED);
    }

    @DeleteMapping("/contatos/{id}")
    public ResponseEntity<?> deleteContato(@PathVariable(value = "id") long id){
        Optional<Contato> contatoO = contatoRepository.findById(id);
        if (contatoO.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        contatoRepository.delete(contatoO.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/contatos/{id}")
    public ResponseEntity<Contato> editContato(@Valid @PathVariable(value = "id") long id, @RequestBody Contato contato) {
        Optional<Contato> contatoO = contatoRepository.findById(id);
        if (contatoO.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        contato.setId(contatoO.get().getId());
        return new ResponseEntity<>(contatoRepository.save(contato), HttpStatus.OK);
    }
}
