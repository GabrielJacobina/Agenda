package com.agenda.controllers;

import com.agenda.models.Contato;
import com.agenda.models.Usuario;
import com.agenda.repositorys.UsuarioRepository;
import com.agenda.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
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

    @Autowired
    private UsuarioService usuarioService;

    private Boolean login;

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
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> editUsuario(@Validated @PathVariable(value = "id") long id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioO = usuarioRepository.findById(id);
        if (!usuarioO.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usuario.setId(usuarioO.get().getId());
        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.OK);
    }



//    @PostMapping(path = "/usuarios/login", produces = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody Boolean loginUsuario(@Validated @RequestBody Usuario usuario) {
//        login = usuarioService.fazerLogin(usuario);
//        if (login  == false){
//            return Boolean.FALSE;
//        }
//        return Boolean.TRUE;
//    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Boolean getLogin() {
        return login;
    }

    public void setLogin(Boolean login) {
        this.login = login;
    }
}
