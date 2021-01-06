package com.agenda.controllers;

import com.agenda.models.Contato;
import com.agenda.repositorys.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContatoController {

    @Autowired
    private ContatoRepository contatoRepository;

    @GetMapping("/contatos")
    public ResponseEntity<List<Contato>> getAllContatos(){
        List<Contato> contatos = contatoRepository.findAll();
        if (contatos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Contato>>(contatos, HttpStatus.OK);
    }

    @GetMapping("/contatos/{id}")
    public ResponseEntity<Contato> getOneContato(@PathVariable(value = "id") long id){
        Optional<Contato> contatoO = contatoRepository.findById(id);
        if (contatoO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Contato>(contatoO.get(), HttpStatus.OK);
    }

    @PostMapping("/contatos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Contato> createContato(@Validated @RequestBody Contato contato){
        return new ResponseEntity<Contato>(contatoRepository.save(contato), HttpStatus.CREATED);
    }

    @DeleteMapping("/contatos/{id}")
    public ResponseEntity<?> deleteContato(@PathVariable(value = "id") long id){
        Optional<Contato> contatoO = contatoRepository.findById(id);
        if (!contatoO.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        contatoRepository.delete(contatoO.get());
        System.out.println("Rodou um delete id: " + id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/contatos/{id}")
    public ResponseEntity<Contato> editContato(@Validated @PathVariable(value = "id") long id, @RequestBody Contato contato) {
        Optional<Contato> contatoO = contatoRepository.findById(id);
        if (!contatoO.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        contato.setId(contatoO.get().getId());
        System.out.println("Rodou um put id: " + contato.getId() + " e nome: " + contato.getNome());
        return new ResponseEntity<>(contatoRepository.save(contato), HttpStatus.OK);
    }
}
