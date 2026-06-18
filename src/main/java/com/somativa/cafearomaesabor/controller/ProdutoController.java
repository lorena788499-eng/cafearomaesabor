package com.somativa.cafearomaesabor.controller;

import com.somativa.cafearomaesabor.model.Produto;
import com.somativa.cafearomaesabor.repository.ProdutoRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/cadastro-produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Exibe a página de cadastro de produtos com a lista de todos os produtos
     */
    @GetMapping
    public String mostrarCadastroProdutos(HttpSession session, Model model) {
        // Verifica se o usuário está logado
        

        // Obtém todos os produtos ordenados por nome
        List<Produto> produtos = produtoRepository.findByOrderByNomeAsc();
        
        model.addAttribute("produtos", produtos);
        model.addAttribute("nomeUsuario", session.getAttribute("nomeUsuario"));
        model.addAttribute("totalProdutos", produtos.size());

        return "cadastro-produtos";
    }

    /**
     * Realiza o cadastro de um novo produto
     */
    @PostMapping
    public String salvarProduto(
            @RequestParam("codigo") String codigo,
            @RequestParam("nome") String nome,
            @RequestParam("descricao") String descricao,
            @RequestParam("categoria") String categoria,
            @RequestParam("quantidade") Integer quantidade,
            @RequestParam("estoque_minimo") Integer estoqueMinimo,
            @RequestParam("lote") String lote,
            @RequestParam("data_validade") LocalDate dataValidade,
            HttpSession session,
            Model model) {



        try {
            // Verifica se o código já existe
            if (produtoRepository.findByCodigo(codigo) != null) {
                model.addAttribute("erro", "Código do produto já existe");
                return mostrarCadastroProdutos(session, model);
            }

            // Cria um novo produto
            Produto novoProduto = new Produto();
            novoProduto.setCodigo(codigo);
            novoProduto.setNome(nome);
            novoProduto.setDescricao(descricao);
            novoProduto.setCategoria(categoria);
            novoProduto.setQuantidade(quantidade);
            novoProduto.setEstoqueMinimo(estoqueMinimo);
            novoProduto.setLote(lote);
            novoProduto.setDataValidade(dataValidade);

            // Salva o produto
            produtoRepository.save(novoProduto);

            model.addAttribute("sucesso", "Produto cadastrado com sucesso!");
            return mostrarCadastroProdutos(session, model);

        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao cadastrar produto: " + e.getMessage());
            return mostrarCadastroProdutos(session, model);
        }
    }

    /**
     * Busca produtos por nome
     */
    @GetMapping("/buscar")
    public String buscarProduto(
            @RequestParam("nome") String nome,
            HttpSession session,
            Model model) {


        List<Produto> produtosBuscados = produtoRepository.findByNomeContainingIgnoreCase(nome);
        
        model.addAttribute("produtos", produtosBuscados);
        model.addAttribute("nomeUsuario", session.getAttribute("nomeUsuario"));
        model.addAttribute("totalProdutos", produtosBuscados.size());

        return "cadastro-produtos";
    }

    /**
     * Atualiza um produto existente
     */
    @PostMapping("/{id}")
    public String atualizarProduto(
            @PathVariable Long id,
            @RequestParam("nome") String nome,
            @RequestParam("descricao") String descricao,
            @RequestParam("categoria") String categoria,
            @RequestParam("estoque_minimo") Integer estoqueMinimo,
            @RequestParam("lote") String lote,
            @RequestParam("data_validade") LocalDate dataValidade,
            HttpSession session,
            Model model) {


        try {
            Produto produto = produtoRepository.findById(id).orElse(null);

            if (produto == null) {
                model.addAttribute("erro", "Produto não encontrado");
                return mostrarCadastroProdutos(session, model);
            }

            produto.setNome(nome);
            produto.setDescricao(descricao);
            produto.setCategoria(categoria);
            produto.setEstoqueMinimo(estoqueMinimo);
            produto.setLote(lote);
            produto.setDataValidade(dataValidade);

            produtoRepository.save(produto);

            model.addAttribute("sucesso", "Produto atualizado com sucesso!");
            return mostrarCadastroProdutos(session, model);

        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao atualizar produto: " + e.getMessage());
            return mostrarCadastroProdutos(session, model);
        }
    }

    /**
     * Deleta um produto
     */
    @GetMapping("/deletar/{id}")
    public String deletarProduto(
            @PathVariable Long id,
            HttpSession session,
            Model model) {


        try {
            produtoRepository.deleteById(id);
            model.addAttribute("sucesso", "Produto deletado com sucesso!");
            return mostrarCadastroProdutos(session, model);

        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao deletar produto: " + e.getMessage());
            return mostrarCadastroProdutos(session, model);
        }
    }
}

