package com.spring.cevento.configuration;

import com.spring.cevento.model.Role;
import com.spring.cevento.model.Usuario;
import com.spring.cevento.repository.RoleRepository;
import com.spring.cevento.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.Arrays;

public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
        boolean alreadySetup = false;


        @Autowired
        private RoleRepository roleRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private UsuarioRepository usuarioRepository;

@Override
public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
        return;

        createRoleIfNotFound("ROLE_MODERA");
        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_USER");
/*
        Role role= new Role();
        role.setNomeRole("ROLE_MODERA");
        roleRepository.save(role);
        role.setNomeRole("ROLE_ADMIN");
        roleRepository.save(role);
        role.setNomeRole("ROLE_USER");
        roleRepository.save(role);


        Role moderaRole = roleRepository.getByRole("ROLE_ADMIN");
        Usuario usuario= new Usuario();
        usuario.setLogin("moderador");
        usuario.setEmail("modera@gmail.com");
        usuario.setSenha(passwordEncoder.encode("senha"));
        usuario.setRoles(Arrays.asList(moderaRole));
        usuarioRepository.save(usuario);
*/
        alreadySetup = true;

        }
@Transactional
    Role createRoleIfNotFound( String name) {

            Role role = roleRepository.getByRole(name);
            if (role == null) {
            role = new Role();
            role.setNomeRole(name);
            roleRepository.save(role);
            }
            return role;
            }

            }
