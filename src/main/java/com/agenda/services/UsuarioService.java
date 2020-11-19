package com.agenda.services;

import com.agenda.models.Usuario;
import com.agenda.repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> buscarUsuarios(){
        return usuarioRepository.findAll();
    }

    public Boolean fazerLogin(Usuario usuario){
        List<Usuario> usuarios = this.buscarUsuarios();
        for (Usuario usuario1 : usuarios) {
            if (usuario.getUsuario().equals(usuario1.getUsuario()) && usuario.getSenha().equals(usuario1.getSenha())) {
                return true;
            }
        }
        return false;
    }

}
