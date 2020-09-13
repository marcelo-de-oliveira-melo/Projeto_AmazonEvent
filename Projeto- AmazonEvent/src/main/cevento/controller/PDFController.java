package com.spring.cevento.controller;

import com.lowagie.text.DocumentException;
import com.spring.cevento.model.Evento;
import com.spring.cevento.model.PartPDFExport;
import com.spring.cevento.model.PartPDFPresenca;
import com.spring.cevento.model.Participante;
import com.spring.cevento.repository.CeventoRepository;
import com.spring.cevento.repository.ParticipanteRepository;
import com.spring.cevento.repository.UsuarioRepository;
import com.spring.cevento.service.serviceImpl.CeventoServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PDFController {
    @Autowired
    CeventoRepository ceventoRepository;
    @Autowired
    ParticipanteRepository pr;
    @Autowired
    CeventoServicelmpl service;
    @Autowired
    UsuarioRepository usuarioRepository;


    // impressao do PDF da lista de participantes
    @GetMapping("/participantes/{id}")
    public void exportToPDF(@PathVariable("id")long id, HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=ListaParticipantes_"+currentDateTime+".pdf";
        response.setHeader(headerKey, headerValue);

        Evento evento = service.get(id);
        Iterable<Participante> listpart = pr.findByEvento(evento);

        PartPDFExport exporter = new PartPDFExport(listpart);
        exporter.export(response);
    }
    // impressao do PDF da lista de presença de participantes
    @GetMapping("/presenca/{id}")
    public void exportToPDFPresenca(@PathVariable ("id")long id,HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=ListaPresença_"+currentDateTime+".pdf";
        response.setHeader(headerKey, headerValue);

        Evento evento = service.get(id);
        Iterable<Participante> listparte = pr.findByEvento(evento);

        PartPDFPresenca presenca = new PartPDFPresenca(listparte);
        presenca.presencas(response);
    }

}
