package com.spring.cevento.repository;

import com.spring.cevento.model.Evento;
import com.spring.cevento.model.Participante;
import com.spring.cevento.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CeventoRepository extends PagingAndSortingRepository<Evento,Long> {

    Evento findById(long id);


    @Query(value = "select * from evento e where e.nome like %:keyword%", nativeQuery = true)
    List<Evento> findByKeyword(@Param("keyword") String keyword);





}
