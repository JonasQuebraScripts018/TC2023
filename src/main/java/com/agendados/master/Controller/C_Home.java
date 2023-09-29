package com.agendados.master.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
@Controller
public class C_Home{
    @GetMapping("/Home")
    public String getHome(HttpSession session){
        if(session.getAttribute("cnpj") != null){
            return "Home/Home";
        }else{
            return "redirect:/";
        }
    }
}