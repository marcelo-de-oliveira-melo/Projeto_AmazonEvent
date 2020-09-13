package com.spring.cevento.controller;

import com.spring.cevento.model.Usuario;
import com.spring.cevento.repository.RoleRepository;
import com.spring.cevento.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
public class UsuarioController {
    @Autowired
    UsuarioRepository ur;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

// mostra tela de cadastro
    @GetMapping(value="/cadastro")
    public String exibirCadastro(){
        return "cadastro";
    }

// valida o cadastro
    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public String saveUsuario(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagemErro", "Verifique se os campos obrigatorios foram preenchidos!");
            return "redirect:/cadastro";
        }
        //setta o role
        usuario.setRoles(Arrays.asList(roleRepository.getByRole("ROLE_USER")));
        // codifica a senha
        String codificada = passwordEncoder.encode(usuario.getPassword());
        usuario.setSenha(codificada);

        ur.save(usuario);
        attributes.addFlashAttribute("mensagem", "Usuário registrado com sucesso");
        return "redirect:/login";
    }

//tela login
   @RequestMapping(value={"/login"}, method = RequestMethod.GET)
   public ModelAndView login(@RequestParam(value = "error", required = false) boolean error){
       ModelAndView mv = new ModelAndView();
       if (error == true) {
          System.out.println("algo de errado");

       }
       else mv.setViewName("login");

       return mv;
   }
    //perfil do ususario
    @RequestMapping(value="/minhaConta/{login}", method = RequestMethod.GET)
    public ModelAndView getPerfil(@PathVariable(value = "login") String login){
        Usuario usuario =  ur.getUsuarioByLogin(login);
        ModelAndView mv= new ModelAndView("minhaConta");
        mv.addObject("usuario", usuario);
        return mv;
    }
}
