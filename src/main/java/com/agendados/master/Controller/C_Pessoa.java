package com.agendados.master.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agendados.master.Model.M_Pessoa;
import com.agendados.master.Service.S_Pessoa;

import jakarta.servlet.http.HttpSession;


@Controller
public class C_Pessoa {
    @GetMapping("/")
    public String getlogin(){
        return "Login/Login";
    }

    @PostMapping("/")
    public String postLogin(@RequestParam("usuario") String usuario,
                            @RequestParam("senha") String senha,
                            HttpSession session,
                            RedirectAttributes redirectAttributes){
        M_Pessoa pessoa = S_Pessoa.getPessoaLogin(usuario, senha);
        session.setAttribute("usuario", pessoa);
        
        if(session.getAttribute("usuario") == null){
            return "Login/Login";
        }else {
            redirectAttributes.addFlashAttribute("nome", pessoa.getNome());
            return "redirect:/Home";
        }
    }

    @ModelAttribute("usuario")
    public M_Pessoa getUsuario(HttpSession session){
        return (M_Pessoa) session.getAttribute("usuario");
    }


}