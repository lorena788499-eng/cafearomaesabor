package com.somativa.cafearomaesabor.controller;

import com.somativa.cafearomaesabor.model.Produto;
import com.somativa.cafearomaesabor.model.MovimentacaoEstoque;
import com.somativa.cafearomaesabor.model.Usuario;
import com.somativa.cafearomaesabor.repository.ProdutoRepository;
import com.somativa.cafearomaesabor.repository.MovimentacaoEstoqueRepository;
import com.somativa.cafearomaesabor.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/gestao-estoque")
public class EstoqueController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Exibe a página de gestão de estoque
     */
    @GetMapping
    public String mostrarGestaoEstoque(HttpSession session, Model model) {
        // Verifica se o usuário está logado

        // Obtém todos os produtos ordenados por nome
        List<Produto> produtos = produtoRepository.findByOrderByNomeAsc();

        // Obtém produtos em alerta
        //List<Produto> produtosAlerta = produtoRepository.findProdutosEmAlerta();

        // Obtém o histórico de movimentações (últimas 10)
        List<MovimentacaoEstoque> movimentacoes = movimentacaoEstoqueRepository.findTop10ByOrderByDataMovimentacaoDescIdDesc();

        // Calcula estatísticas
        long situacaoOk = produtos.stream().filter(p -> !p.isEstoqueMinimo()).count();
        long alertaMinimo = produtos.stream().filter(p -> p.isEstoqueMinimo() && !p.isEstoqueCritico()).count();
        long critico = produtos.stream().filter(p -> p.isEstoqueCritico()).count();

        model.addAttribute("produtos", produtos);
        model.addAttribute("produtosAlerta", 20);
        model.addAttribute("movimentacoes", movimentacoes);
        model.addAttribute("nomeUsuario", session.getAttribute("nomeUsuario"));
        model.addAttribute("situacaoOk", situacaoOk);
        model.addAttribute("alertaMinimo", alertaMinimo);
        model.addAttribute("critico", critico);
        model.addAttribute("totalProdutos", produtos.size());

        return "gestao-estoque";
    }

    /**
     * Registra uma movimentação de entrada ou saída de produtos
     */
    @PostMapping
    public String registrarMovimentacao(
            @RequestParam("produto") Long produtoId,
            @RequestParam("tipo_movimentacao") String tipo,
            @RequestParam("quantidade") Integer quantidade,
            @RequestParam("data_movimentacao") LocalDate dataMovimentacao,
            @RequestParam(value = "observacoes", required = false) String observacoes,
            HttpSession session,
            Model model) {

        try {
            // Busca o produto
            Produto produto = produtoRepository.findById(produtoId).orElse(null);
            if (produto == null) {
                model.addAttribute("erro", "Produto não encontrado");
                return mostrarGestaoEstoque(session, model);
            }

            // Busca o usuário logado
            Long usuarioId = (Long) session.getAttribute("idUsuario");
            Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
            if (usuario == null) {
                model.addAttribute("erro", "Usuário não encontrado");
                return mostrarGestaoEstoque(session, model);
            }

            // Valida a quantidade
            if (quantidade <= 0) {
                model.addAttribute("erro", "Quantidade deve ser maior que zero");
                return mostrarGestaoEstoque(session, model);
            }

            // Atualiza a quantidade do produto
            if (tipo.equals("ENTRADA")) {
                produto.setQuantidade(produto.getQuantidade() + quantidade);
            } else if (tipo.equals("SAIDA")) {
                if (produto.getQuantidade() < quantidade) {
                    model.addAttribute("erro", "Quantidade em estoque insuficiente");
                    return mostrarGestaoEstoque(session, model);
                }
                produto.setQuantidade(produto.getQuantidade() - quantidade);
            } else {
                model.addAttribute("erro", "Tipo de movimentação inválido");
                return mostrarGestaoEstoque(session, model);
            }

            // Salva o produto atualizado
            produtoRepository.save(produto);

            // Registra a movimentação
            MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();
            movimentacao.setProduto(produto);
            movimentacao.setUsuario(usuario);
            movimentacao.setTipo(MovimentacaoEstoque.TipoMovimentacao.valueOf(tipo));
            movimentacao.setQuantidade(quantidade);
            movimentacao.setDataMovimentacao(dataMovimentacao);
            movimentacao.setObservacoes(observacoes);

            movimentacaoEstoqueRepository.save(movimentacao);

            model.addAttribute("sucesso", "Movimentação registrada com sucesso!");
            return mostrarGestaoEstoque(session, model);

        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao registrar movimentação: " + e.getMessage());
            return mostrarGestaoEstoque(session, model);
        }
    }

    /**
     * Busca produtos por código ou nome
     */
    @GetMapping("/buscar")
    public String buscarProdutoEstoque(
            @RequestParam("termo") String termo,
            HttpSession session,
            Model model) {

        try {
            List<Produto> produtos = produtoRepository.findByNomeContainingIgnoreCase(termo);

            if (produtos.isEmpty()) {
                Produto produtoPorCodigo = produtoRepository.findByCodigo(termo);
                if (produtoPorCodigo != null) {
                    produtos.add(produtoPorCodigo);
                }
            }

            List<MovimentacaoEstoque> movimentacoes = movimentacaoEstoqueRepository.findTop10ByOrderByDataMovimentacaoDescIdDesc();

            model.addAttribute("produtos", produtos);
            model.addAttribute("movimentacoes", movimentacoes);
            model.addAttribute("nomeUsuario", session.getAttribute("nomeUsuario"));
            model.addAttribute("totalProdutos", produtos.size());

            return "gestao-estoque";

        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao buscar produtos: " + e.getMessage());
            return mostrarGestaoEstoque(session, model);
        }
    }

    /**
     * Exibe o histórico de movimentações
     */
    @GetMapping("/historico")
    public String verHistorico(HttpSession session, Model model) {

        List<MovimentacaoEstoque> movimentacoes = movimentacaoEstoqueRepository.findTop10ByOrderByDataMovimentacaoDescIdDesc();
        List<Produto> produtos = produtoRepository.findByOrderByNomeAsc();

        model.addAttribute("movimentacoes", movimentacoes);
        model.addAttribute("produtos", produtos);
        model.addAttribute("nomeUsuario", session.getAttribute("nomeUsuario"));

        return "gestao-estoque";
    }
}

