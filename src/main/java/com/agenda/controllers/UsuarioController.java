package com.agenda.controllers;

import com.agenda.models.Contato;
import com.agenda.models.Usuario;
import com.agenda.repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getAllUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> getOneUsuario(@PathVariable(value = "id") long id){
        Optional<Usuario> usuarioO = usuarioRepository.findById(id);
        if (usuarioO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Usuario>(usuarioO.get(), HttpStatus.OK);
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> createUsuario(@Validated @RequestBody Usuario usuario){
        return new ResponseEntity<Usuario>(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable(value = "id") long id){
        Optional<Usuario> usuarioO = usuarioRepository.findById(id);
        if (!usuarioO.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usuarioRepository.delete(usuarioO.get());
        System.out.println("Rodou um delete id: " + id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> editUsuario(@Validated @PathVariable(value = "id") long id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioO = usuarioRepository.findById(id);
        if (!usuarioO.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usuario.setId(usuarioO.get().getId());
        System.out.println("Rodou um put id: " + usuario.getId() + " e nome: " + usuario.getUsuario());
        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.OK);
    }
}