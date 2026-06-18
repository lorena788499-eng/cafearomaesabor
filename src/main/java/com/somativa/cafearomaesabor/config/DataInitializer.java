package com.somativa.cafearomaesabor.config;

import com.somativa.cafearomaesabor.model.Produto;
import com.somativa.cafearomaesabor.model.Usuario;
import com.somativa.cafearomaesabor.repository.ProdutoRepository;
import com.somativa.cafearomaesabor.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Bean
    public CommandLineRunner initializeData() {
        return args -> {
            // Verifica se já existem usuários
            if (usuarioRepository.count() == 0) {
                // Cria usuário de teste
                Usuario admin = new Usuario();
                admin.setNomeUsuario("admin");
                admin.setSenha("123456");
                admin.setNomeCompleto("Administrador do Sistema");
                admin.setEmail("admin@cafe.com");
                admin.setAtivo(true);
                usuarioRepository.save(admin);

                // Cria usuário adicional
                Usuario usuario2 = new Usuario();
                usuario2.setNomeUsuario("joao");
                usuario2.setSenha("123456");
                usuario2.setNomeCompleto("João Silva");
                usuario2.setEmail("joao@cafe.com");
                usuario2.setAtivo(true);
                usuarioRepository.save(usuario2);

                System.out.println("✓ Usuários iniciais criados com sucesso!");
            }

            // Verifica se já existem produtos
            if (produtoRepository.count() == 0) {
                // Produtos de teste
                Produto[] produtos = {
                    criarProduto("CAF001", "Café Arábica Premium", "Café de origem única, arábica 100%", "cafe", 45, 20, "L12345", LocalDate.of(2025, 12, 25)),
                    criarProduto("CAF002", "Café Robusta Forte", "Café robusta com corpo forte", "cafe", 30, 15, "L12346", LocalDate.of(2025, 8, 10)),
                    criarProduto("CAF003", "Café Moído Espresso", "Café moído para máquina espresso", "cafe_moido", 12, 25, "L12347", LocalDate.of(2025, 6, 30)),
                    criarProduto("CHA001", "Chá Verde Premium", "Chá verde de alta qualidade", "cha", 28, 10, "L12348", LocalDate.of(2025, 10, 15)),
                    criarProduto("ACC001", "Moedor de Café Manual", "Moedor manual para café grão", "acessorios", 8, 5, "L12349", LocalDate.of(2026, 12, 31)),
                    criarProduto("ALI001", "Açúcar Cristal", "Açúcar cristal refinado", "alimentos", 50, 30, "L12350", LocalDate.of(2026, 5, 20)),
                    criarProduto("CAF004", "Café Torrado Médio", "Café torrado ponto médio", "cafe", 35, 20, "L12351", LocalDate.of(2025, 11, 18)),
                    criarProduto("CAF005", "Café Descafeinado", "Café descafeinado premium", "cafe", 22, 15, "L12352", LocalDate.of(2025, 9, 14)),
                    criarProduto("ACC002", "Filtro de Café Reutilizável", "Filtro de inox reutilizável", "acessorios", 60, 30, "L12353", LocalDate.of(2026, 12, 31)),
                    criarProduto("ALI002", "Leite em Pó Integral", "Leite em pó integral", "alimentos", 24, 15, "L12354", LocalDate.of(2026, 3, 12)),
                    criarProduto("CHA002", "Chá Preto Inglês", "Chá preto tipo inglês clássico", "cha", 18, 12, "L12355", LocalDate.of(2025, 7, 22)),
                    criarProduto("ACC003", "Xícara de Café Cerâmica", "Xícara de cerâmica resistente", "acessorios", 100, 50, "L12356", LocalDate.of(2027, 12, 31))
                };

                for (Produto produto : produtos) {
                    produtoRepository.save(produto);
                }

                System.out.println("✓ Produtos iniciais criados com sucesso!");
            }
        };
    }

    private Produto criarProduto(String codigo, String nome, String descricao, String categoria,
                                  Integer quantidade, Integer estoqueMinimo, String lote, LocalDate dataValidade) {
        Produto produto = new Produto();
        produto.setCodigo(codigo);
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setCategoria(categoria);
        produto.setQuantidade(quantidade);
        produto.setEstoqueMinimo(estoqueMinimo);
        produto.setLote(lote);
        produto.setDataValidade(dataValidade);
        return produto;
    }
}

