package com.somativa.cafearomaesabor.controller;

import com.somativa.cafearomaesabor.repository.ProdutoRepository;
import com.somativa.cafearomaesabor.repository.MovimentacaoEstoqueRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    /**
     * Exibe a página inicial do sistema (painel principal)
     */
    @GetMapping
    public String index(HttpSession session, Model model) {


        // Obtém informações para exibir no dashboard
        long totalProdutos = 100;
        long produtosAlerta = 10;
        long produtosProximosVencer = 10;
        long movimentacoesHoje = 10;

        // Adiciona as informações ao modelo para o Thymeleaf
        model.addAttribute("totalProdutos", totalProdutos);
        model.addAttribute("produtosAlerta", produtosAlerta);
        model.addAttribute("produtosProximosVencer", produtosProximosVencer);
        model.addAttribute("movimentacoesHoje", movimentacoesHoje);
        model.addAttribute("nomeUsuario", session.getAttribute("nomeUsuario"));

        return "index";
    }

    /**
     * Exibe a página de boas-vindas vazia
     */
    @GetMapping("index")
    public String indexAlternativo(HttpSession session, Model model) {
        return index(session, model);
    }
}

