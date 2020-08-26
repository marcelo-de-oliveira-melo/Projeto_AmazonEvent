package com.spring.cevento.repository;

import com.spring.cevento.model.Evento;
import com.spring.cevento.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CeventoRepository extends JpaRepository<Evento,Long> {

    Evento findById(long id);

}
