package com.spring.cevento.repository;

import com.spring.cevento.model.Evento;
import com.spring.cevento.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario,String> {

    Usuario getUsuarioByLogin(String login);

    @Query(value = "select * from usuario u where u.login like %:usuario%", nativeQuery = true)
    List<Usuario> findUsuario(@Param("usuario") String usuario);


}
