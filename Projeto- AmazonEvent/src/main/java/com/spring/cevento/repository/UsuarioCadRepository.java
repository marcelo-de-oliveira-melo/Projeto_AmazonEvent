package com.spring.cevento.repository;

import com.spring.cevento.model.Evento;
import com.spring.cevento.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioCadRepository extends CrudRepository<Usuario,String> {

    public Usuario findByLoginAndSenha(String login, String senha);


             Usuario getUsuarioByLogin(String login);
}
