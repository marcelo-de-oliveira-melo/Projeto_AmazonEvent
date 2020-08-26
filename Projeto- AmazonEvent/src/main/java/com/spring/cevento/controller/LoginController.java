package com.spring.cevento.controller;

import com.spring.cevento.model.Usuario;
import com.spring.cevento.repository.UsuarioCadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
/*
  @Autowired
    private UsuarioCadRepository ur;

    @GetMapping(value="/login")
    public String exibirLogin(Usuario usuario){

       return "login";
    }
    @PostMapping("/efetuaLogin")
    public String efetuarLogin(Usuario usuario, RedirectAttributes ra, HttpSession session){
        usuario = this.ur.findByLoginAndSenha(usuario.getLogin(), usuario.getSenha());
        if(usuario != null){
            //guardar sessao o usuario
            session.setAttribute("usuarioLogado", usuario);
            return "redirect:/eventos";
        }else{
            ra.addFlashAttribute("mensagem", "Login/senha invalidos");
            return "redirect:/login";
        }
    }


*/
}
