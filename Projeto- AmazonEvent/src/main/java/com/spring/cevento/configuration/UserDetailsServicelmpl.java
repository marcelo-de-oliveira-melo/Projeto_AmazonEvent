package com.spring.cevento.configuration;

import com.spring.cevento.model.Usuario;
import com.spring.cevento.repository.UsuarioCadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class UserDetailsServicelmpl implements UserDetailsService {

    @Autowired
    private UsuarioCadRepository ur;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = ur.getUsuarioByLogin(login);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario não encontrado!");
        }

        return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());
    }


}
