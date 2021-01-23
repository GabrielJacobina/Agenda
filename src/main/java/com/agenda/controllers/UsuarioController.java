package com.agenda.controllers;

import com.agenda.models.Usuario;
import com.agenda.repositorys.UsuarioRepository;
import com.agenda.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getOneUsuario(@PathVariable(value = "id") long id){
        Optional<Usuario> usuarioO = usuarioRepository.findById(id);
        if (usuarioO.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuarioO.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@Validated @RequestBody Usuario usuario){
        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable(value = "id") long id){
        Optional<Usuario> usuarioO = usuarioRepository.findById(id);
        if (usuarioO.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usuarioRepository.delete(usuarioO.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> editUsuario(@Validated @PathVariable(value = "id") long id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioO = usuarioRepository.findById(id);
        if (usuarioO.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usuario.setId(usuarioO.get().getId());
        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.OK);
    }

}
