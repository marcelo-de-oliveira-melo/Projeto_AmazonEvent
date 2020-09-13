package com.spring.cevento.controller;

import com.lowagie.text.DocumentException;
import com.spring.cevento.model.*;
import com.spring.cevento.repository.CeventoRepository;
import com.spring.cevento.repository.ParticipanteRepository;
import com.spring.cevento.repository.UsuarioRepository;
import com.spring.cevento.service.serviceImpl.CeventoServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CeventoController {
    @Autowired
    CeventoRepository ceventoRepository;
    @Autowired
    ParticipanteRepository pr;
    @Autowired
    CeventoServicelmpl service;
    @Autowired
    UsuarioRepository usuarioRepository;



//esse metodo vai retornar uma listagem de eventos
    @RequestMapping(value={"/eventos","/"}, method = RequestMethod.GET)
    public ModelAndView getEventos( @PageableDefault(sort ="id",direction = Sort.Direction.DESC,value = 20) Pageable pageable,String keyword){
        ModelAndView mv= new ModelAndView("eventos");


        if (keyword != null){
            List<Evento> eventos = service.findByKeyword(keyword);
            mv.addObject("eventos",eventos);
        }
        else{
            Page<Evento> eventos = ceventoRepository.findAll(pageable);
            mv.addObject("eventos", eventos);
        }


        return mv;
    }

//esse metodo vai retornar um evento especifico na página principal
    @RequestMapping(value="/eventos/{id}", method = RequestMethod.GET)
    public ModelAndView getEventoDetails(@PathVariable("id") long id){
        ModelAndView mv= new ModelAndView("eventoDetails");
        Evento evento =  ceventoRepository.findById(id);
        mv.addObject("evento", evento);

        Iterable<Participante> participantes = pr.findByEvento(evento);
        mv.addObject("participantes", participantes);
        return mv;
    }

//esse metodo vai retornar uma listagem de eventos na pagina meus eventos
    @RequestMapping(value="/meuseventos", method = RequestMethod.GET)
    public ModelAndView getMeusEventos(@PageableDefault(sort ="id",direction = Sort.Direction.DESC,value = 20) Pageable pageable,String keyword){
    ModelAndView mv= new ModelAndView("listaEvento");
    if (keyword != null){
        List<Evento> eventos = service.findByKeyword(keyword);
        mv.addObject("eventos",eventos);
    }
    else{
        Page<Evento> eventos = ceventoRepository.findAll(pageable);
        mv.addObject("eventos", eventos);
    }
    return mv;
}

//esse metodo vai retornar os participantes de um evento especifico pela pagina meus eventos
    @RequestMapping(value="/meuseventos/{id}", method = RequestMethod.GET)
    public Object getDetails(@PathVariable("id") long id,@RequestParam (value = "palavra", defaultValue = "")String palavra){
        ModelAndView mv= new ModelAndView("listaParticipantes");
        //pega o id do evento
        Evento evento =  ceventoRepository.findById(id);
        mv.addObject("evento", evento);

        //pega os participantes q estão naquele evento
        Iterable<Participante> participantes = pr.findByEvento(evento);
        mv.addObject("participantes", participantes);

        if (palavra != null){
            Iterable<Participante>  resultado = service.findByPalavra(id, palavra);
            mv.addObject("participantes",resultado );
        }
        return mv;
    }

//esse metodo vai retornar uma listagem de todos os eventos (moderador)
    @RequestMapping(value="/todosEventos", method = RequestMethod.GET)
    public ModelAndView getTodosEventos(@PageableDefault(sort ="id",direction = Sort.Direction.DESC,value = 20) Pageable pageable,String keyword){
        ModelAndView mv= new ModelAndView("todosEventos");
        if (keyword != null){
            List<Evento> eventos = service.findByKeyword(keyword);
            mv.addObject("eventos",eventos);
        }
        else{
            Page<Evento> eventos = ceventoRepository.findAll(pageable);
            mv.addObject("eventos", eventos);
        }
        return mv;
    }

//esse metodo vai retornar os participantes de um evento especifico pela pagina todosEventos (moderador)
    @RequestMapping(value="/todosEventos/{id}", method = RequestMethod.GET)
    public Object getDetalhes(@PathVariable("id") long id,@RequestParam (value = "palavra", defaultValue = "")String palavra){
        ModelAndView mv= new ModelAndView("listaParticipantes");
        //pega o id do evento
        Evento evento =  ceventoRepository.findById(id);
        mv.addObject("evento", evento);

        //pega os participantes q estão naquele evento
        Iterable<Participante> participantes = pr.findByEvento(evento);
        mv.addObject("participantes", participantes);

        if (palavra != null){
            Iterable<Participante>  resultado = service.findByPalavra(id, palavra);
            mv.addObject("participantes",resultado );
        }
        return mv;
    }

//mostrar a pagina de criação de um evento
    @RequestMapping(value="/newevento", method = RequestMethod.GET)
    public String getEventoForm(){
        return "eventoForm";
    }

//mostra os detalhes do evento ao clicar e um evento na página principal
    @RequestMapping(value="/eventos/{id}", method= RequestMethod.POST)
    public String detalhesEventoPost(@PathVariable("id") long id, @Valid Participante participante,  BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");
            return "redirect:/{id}";
        }
        Evento evento =  ceventoRepository.findById(id);
        participante.setEvento(evento);
        pr.save(participante);
        attributes.addFlashAttribute("mensagem", "adicionado com sucesso!");
        return "redirect:/eventos/{id}";
    }

//esse metodo vai retornar uma listagem de todos os usuarios(moderador)
    @RequestMapping(value="/todosUsuarios", method = RequestMethod.GET)
    public ModelAndView getTodosUsuarios(@PageableDefault(sort ="login",direction = Sort.Direction.DESC,value = 20) Pageable pageable,String usuario){
        ModelAndView mv= new ModelAndView("todosUsuarios");
        if (usuario != null){
           List<Usuario> usuarios = service.findUsuario(usuario);
           mv.addObject("usuarios",usuarios);
        }
        else{

            Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
            mv.addObject("usuarios", usuarios);
       }
        return mv;
    }





}




