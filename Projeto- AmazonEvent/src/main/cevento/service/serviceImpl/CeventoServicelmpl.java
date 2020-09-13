package com.spring.cevento.service.serviceImpl;

import com.spring.cevento.model.Evento;
import com.spring.cevento.model.Participante;
import com.spring.cevento.model.Usuario;
import com.spring.cevento.repository.CeventoRepository;
import com.spring.cevento.repository.ParticipanteRepository;
import com.spring.cevento.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class CeventoServicelmpl {

    @Autowired
    private CeventoRepository repository;
    @Autowired
    UsuarioRepository usu;
    @Autowired
    ParticipanteRepository part;


    public Iterable<Evento> listAll() {
        return repository.findAll();
    }
    public void save(Evento evento) {
        repository.save(evento);
    }
    public Evento get(long id) {
        return repository.findById(id);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    //getPerfil
    public Usuario get(String login){
            return usu.getUsuarioByLogin(login);
    }

 //pesquisa evento
    public List<Evento> findByKeyword(String keyword){
        return repository.findByKeyword(keyword);

    }
    //pesquisa participante
    public List<Participante> findByPalavra(long id,String palavra){
        return part.findByPalavra(id, palavra);

    }
    //pesquisa participante
    public List<Usuario> findUsuario(String usuario){
        return usu.findUsuario(usuario);

    }

}
