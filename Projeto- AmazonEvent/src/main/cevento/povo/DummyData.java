package com.spring.cevento.povo;

import com.spring.cevento.model.Evento;
import com.spring.cevento.repository.CeventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DummyData {

    @Autowired
    CeventoRepository ceventoRepository;

    //@PostConstruct
    public void saveEventos() {

        List<Evento> eventoList = new ArrayList<>();
        Evento eventol = new Evento();
        eventol.setNome("estudeee");
        eventol.setCategoria("palestra");
        eventol.setDescricao("uma palestra ministrada por tace kk");
        eventol.setLocal("Manaus");
        eventol.setEndereco("av 7 de setembro, centro");
        eventol.setComplemento("perto do ifam");


        eventoList.add(eventol);


        for (Evento evento: eventoList){
            Evento eventoSaved = ceventoRepository.save(evento);
            System.out.println(eventoSaved.getId());
        }

    }


}