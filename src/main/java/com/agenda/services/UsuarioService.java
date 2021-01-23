package com.agenda.services;

import com.agenda.dto.UsuarioDTO;
import com.agenda.mapper.UsuarioMapper;
import com.agenda.models.Usuario;
import com.agenda.repositorys.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        return Optional.ofNullable(usuarioRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    public Page<Usuario> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public Usuario findById(long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado"));
    }

    public Usuario save(UsuarioDTO usuarioDTO) {
        return usuarioRepository.save(UsuarioMapper.INSTANCE.toUsuario(usuarioDTO));
    }

    public void delete(long id) {
        usuarioRepository.delete(findById(id));
    }

    public void replace(UsuarioDTO usuarioDTO) {
        Usuario savedUsuario = findById(usuarioDTO.getId());
        Usuario usuario = UsuarioMapper.INSTANCE.toUsuario(usuarioDTO);
        usuario.setId(savedUsuario.getId());
        usuarioRepository.save(usuario);
    }
}
