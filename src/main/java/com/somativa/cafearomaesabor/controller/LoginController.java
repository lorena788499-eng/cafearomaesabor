package com.somativa.cafearomaesabor.controller;

import com.somativa.cafearomaesabor.model.Usuario;
import com.somativa.cafearomaesabor.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Exibe a página de login
     */
    @GetMapping("/login")
    public String mostrarLogin(HttpSession session) {
        // Se o usuário já estiver logado, redireciona para a página inicial
        return "login";
    }

    // O processamento de login é realizado pelo Spring Security (formLogin em /login).
    // Mantemos apenas o GET para exibir a página de login.

    /**
     * Realiza o logout do usuário
     */
    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}

