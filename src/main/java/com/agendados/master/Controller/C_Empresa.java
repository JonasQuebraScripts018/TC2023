package com.agendados.master.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class C_Empresa {

    @GetMapping("/LoginEmpresa")
    public String getLoginEmpresa(){
        return "Login/LoginEmpresa";
    }

    @PostMapping("/LoginEmpresa")
    public String postLoginEmpresa(@RequestParam("cnpj") String cnpj,
                                   @RequestParam("senha") String senha,
                                   HttpSession session,
                                   RedirectAttributes redirectAttributes){
        return null;
    }

    @GetMapping("/cadastroEmpresa")
    public String getCadastroEmpresa(){
        return "Cadastro/cadastroEmpresa";
    }

    @PostMapping("/cadastroEmpresa")
    public String postCadastroEmpresa(@RequestParam("nome") String nome,
                                      @RequestParam("cnpj") String cnpj,
                                      @RequestParam("email") String email,
                                      @RequestParam("telefone") String telefone,
                                      @RequestParam("cep") String cep,
                                      @RequestParam("senha") String senha,
                                      @RequestParam("confsenha") String confsenha,
                                      RedirectAttributes redirectAttributes){
        return null;
    }
}
