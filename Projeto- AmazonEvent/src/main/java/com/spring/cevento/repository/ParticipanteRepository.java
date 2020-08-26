package com.spring.cevento.repository;

import com.spring.cevento.model.Evento;
import com.spring.cevento.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ParticipanteRepository extends JpaRepository<Participante,Long> {

    Iterable<Participante> findByEvento(Evento evento);
    Participante findByCpf(String cpf);
    Participante findById(long id);


}
