package com.cotacao.seguros.security;

import com.cotacao.seguros.entity.Usuario;
import com.cotacao.seguros.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository repository;

    @Autowired
    public UserDetailsServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsername(username)
                .orElseGet(() -> repository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username)));
        return new User(usuario.getUsername(), usuario.getPassword(), Collections.emptyList());
    }
}
