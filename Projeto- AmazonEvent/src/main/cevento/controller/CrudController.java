package com.spring.cevento.controller;

import com.spring.cevento.model.Evento;
import com.spring.cevento.model.Usuario;
import com.spring.cevento.repository.CeventoRepository;
import com.spring.cevento.repository.ParticipanteRepository;
import com.spring.cevento.repository.UsuarioRepository;
import com.spring.cevento.service.serviceImpl.CeventoServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class CrudController {
    @Autowired
    CeventoRepository ceventoRepository;
    @Autowired
    ParticipanteRepository pr;
    @Autowired
    CeventoServicelmpl service;
    @Autowired
    UsuarioRepository usuarioRepository;
//salvar evento
    @RequestMapping(value = "/newevento", method = RequestMethod.POST)
    public String saveEvento(@Valid Evento evento, @RequestParam("fileEvento") MultipartFile file, BindingResult result, RedirectAttributes attributes){
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

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveEvento(@ModelAttribute("evento") Evento evento, @RequestParam("fileEvento") MultipartFile file,@CurrentSecurityContext(expression="authentication.name")
            Usuario login) {
        if (file.isEmpty()){
            return "redirect:/newevento";
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

        evento.setUsuario(login);

        if (evento.getUsuario()==null){
            return "redirect:/newevento";
        }

        ceventoRepository.save(evento);
        return "redirect:/";
    }

//deletar evento
    @GetMapping("/delete/{id}")
    public String deleteEvento(@PathVariable("id") long id, Model model) {
        Evento evento = ceventoRepository.findById(id);
        ceventoRepository.delete(evento);
        model.addAttribute("evento", ceventoRepository.findAll());
        return "redirect:/meuseventos";
    }

//editar evento
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditEvento(@PathVariable(name = "id") int id) {
        ModelAndView mv = new ModelAndView("editaEvento");
        Evento evento = service.get(id);
        mv.addObject("evento", evento);

        return mv;
    }

}
