package com.spring.cevento.repository;

import com.spring.cevento.model.Evento;
import com.spring.cevento.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

public interface ParticipanteRepository extends JpaRepository<Participante,Long> {
    Iterable<Participante> findByEvento(Evento evento);
    Participante findByCpf(String cpf);
    Participante findById(long id);

    @Query(value = "select * from participante p where p.evento_id=:id and p.nome_participante like %:palavra%", nativeQuery = true)
    List<Participante> findByPalavra(@Param("id") long id, @Param("palavra")  String palavra);




}
