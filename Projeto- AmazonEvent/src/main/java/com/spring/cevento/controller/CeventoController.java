package com.spring.cevento.controller;

import com.lowagie.text.DocumentException;
import com.spring.cevento.model.Evento;
import com.spring.cevento.model.PartPDFExport;
import com.spring.cevento.model.Participante;
import com.spring.cevento.model.Usuario;
import com.spring.cevento.repository.CeventoRepository;
import com.spring.cevento.repository.ParticipanteRepository;
import com.spring.cevento.repository.UsuarioCadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CeventoController {
    @Autowired
    CeventoRepository ceventoRepository;
    @Autowired
    ParticipanteRepository pr;
    @Autowired
    UsuarioCadRepository ur;




//esse metodo vai retornar uma listagem de eventos
    @RequestMapping(value={"/eventos","/"}, method = RequestMethod.GET)
    public ModelAndView getEventos(){
        ModelAndView mv= new ModelAndView("eventos");
        Iterable<Evento> eventos = ceventoRepository.findAll();
        mv.addObject("eventos", eventos);
        return mv;
    }


    //esse metodo vai retornar uma listagem de eventos
    @RequestMapping(value="/meuseventos", method = RequestMethod.GET)
    public ModelAndView getMeusEventos(){
        ModelAndView mv= new ModelAndView("listaEvento");
        Iterable<Evento> eventos = ceventoRepository.findAll();
        mv.addObject("eventos", eventos);
        return mv;
    }

    //esse metodo vai retornar um evento especifico
    @RequestMapping(value="/meuseventos/{id}", method = RequestMethod.GET)
    public ModelAndView getDetails(@PathVariable("id") long id){
        ModelAndView mv= new ModelAndView("ludo");
        Evento evento =  ceventoRepository.findById(id);
        mv.addObject("evento", evento);
        Iterable<Participante> participantes = pr.findByEvento(evento);
        mv.addObject("participantes", participantes);
        return mv;
    }


    //esse metodo vai retornar um evento especifico
    @RequestMapping(value="/eventos/{id}", method = RequestMethod.GET)
    public ModelAndView getEventoDetails(@PathVariable("id") long id){
        ModelAndView mv= new ModelAndView("eventoDetails");
        Evento evento =  ceventoRepository.findById(id);
        mv.addObject("evento", evento);
        Iterable<Participante> participantes = pr.findByEvento(evento);
        mv.addObject("participantes", participantes);
     return mv;
    }
    //mostrar a pagina eventoForm
    @RequestMapping(value="/newevento", method = RequestMethod.GET)
    public String getEventoForm(){
        return "eventoForm";
    }

    //metodo salvar evento
    @RequestMapping(value = "/newevento", method = RequestMethod.POST)
    public String saveEvento(@Valid Evento evento,@RequestParam("fileEvento") MultipartFile file, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("messagem", "Verifique se os campos obrigatorios foram preenchidos!");
            return "redirect:/newevento";
        }
        if (file.isEmpty()){
            return "newevento";
        }
        Path path = Paths.get("uploads/");
        try{
            InputStream inputStream =  file.getInputStream();
            Files.copy(inputStream, path.resolve(file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            evento.setImagem(file.getOriginalFilename().toLowerCase());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ceventoRepository.save(evento);
        attributes.addFlashAttribute("mensagem", "Evento registrado com sucesso");
        return "redirect:/eventos";
    }

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


    @GetMapping(value="/cadastro")
    public String exibirCadastro(){
        return "cadastro";
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public String saveUsuario(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("messagem", "Verifique se os campos obrigatorios foram preenchidos!");
            return "redirect:/cadastro";
        }
        ur.save(usuario);
        attributes.addFlashAttribute("mensagem", "Usuário registrado com sucesso");
        return "redirect:/login";
    }


    @GetMapping("/403")
    public String error403(){
        return "403";
    }

    @GetMapping("/menu")
    public String menuex(){
        return "ado";
    }

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView mv= new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    @GetMapping("/minhaConta")
    public ModelAndView minhaConta(){
        ModelAndView mv= new ModelAndView();
        mv.setViewName("minhaConta");
        return mv;
    }

    @GetMapping("/participantes/export")
    public void exportToPDF(HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=part_"+currentDateTime+".pdf";

        response.setHeader(headerKey, headerValue);
        List<Participante> listpart = pr.findAll();

        PartPDFExport exporter = new PartPDFExport(listpart);
        exporter.export(response);
    }





}




