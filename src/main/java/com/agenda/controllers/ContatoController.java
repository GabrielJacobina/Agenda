package com.agenda.controllers;

import com.agenda.models.Contato;
import com.agenda.repositorys.ContatoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ContatoController {

    @Autowired
    private ContatoDAO contatoDAO;

    @GetMapping("/contatos")
    public ResponseEntity<List<Contato>> getAllContatos(){
        List<Contato> contatos = contatoDAO.findAll();
        if (contatos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Contato>>(contatos, HttpStatus.OK);
    }

    @GetMapping("/contatos/{id}")
    public ResponseEntity<Contato> getOneContato(@PathVariable(value = "id") long id){
        Optional<Contato> contatoO = contatoDAO.findById(id);
        if (contatoO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Contato>(contatoO.get(), HttpStatus.OK);
    }

    @PostMapping("/contatos")
    public ResponseEntity<Contato> createContato(@RequestBody Contato contato){
        return new ResponseEntity<Contato>(contatoDAO.save(contato), HttpStatus.CREATED);
    }

    @DeleteMapping("/contatos/{id}")
    public ResponseEntity<Contato> deleteContato(@PathVariable(value = "id") long id){
        Optional<Contato> contatoO = contatoDAO.findById(id);
        if (!contatoO.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        contatoDAO.delete(contatoO.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/contatos/{id}")
    public ResponseEntity<Contato> editContato(@PathVariable(value = "id") long id, @RequestBody Contato contato) {
        Optional<Contato> contatoO = contatoDAO.findById(id);
        if (!contatoO.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        contato.setId(contatoO.get().getId());
        return new ResponseEntity<>(contatoDAO.save(contato), HttpStatus.OK);
    }
}
