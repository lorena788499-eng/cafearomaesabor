# Etapa 3 - Guia de Instalação e Uso

## ✅ Pré-requisitos

- Java 21+
- Maven 3.6+
- MySQL 8.0+
- IDE (IntelliJ IDEA ou VS Code)

## 🔧 Passo 1: Configuração do Banco de Dados

### Opção A: Configuração Automática (Recomendada)
O Hibernate criará as tabelas automaticamente na primeira execução.

Edite `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/CafeAromaESabor?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=Senha_do_seu_MySQL
```

### Opção B: Criar Manualmente
Se preferir criar manualmente, execute o script em `src/main/resources/database.sql`:

```bash
mysql -u root -p < database.sql
```

## 📥 Passo 2: Instalar Dependências

```bash
mvn clean install
```

## 🚀 Passo 3: Executar a Aplicação

### Opção A: Via Maven
```bash
mvn spring-boot:run
```

### Opção B: Via IDE
- Clique com botão direito em `CafeAromaESaborApplication.java`
- Selecione "Run"

### Opção C: Depois de compilar
```bash
java -jar target/CafeAromaESabor-0.0.1-SNAPSHOT.jar
```

## 🌐 Passo 4: Acessar o Sistema

- **URL:** http://localhost/login
- **Usuário:** admin
- **Senha:** 123456

## 📊 Estrutura de Diretórios Criada

```
src/main/java/com/somativa/cafearomaesabor/
├── model/
│   ├── Usuario.java
│   ├── Produto.java
│   └── MovimentacaoEstoque.java
├── repository/
│   ├── UsuarioRepository.java
│   ├── ProdutoRepository.java
│   └── MovimentacaoEstoqueRepository.java
├── controller/
│   ├── LoginController.java
│   ├── HomeController.java
│   ├── ProdutoController.java
│   └── EstoqueController.java
├── config/
│   └── DataInitializer.java
└── CafeAromaESaborApplication.java

src/main/resources/
├── application.properties
├── database.sql
└── templates/
    ├── login.html
    ├── index.html
    ├── cadastro-produtos.html
    └── gestao-estoque.html
```

## 🧪 Testando os Endpoints

### Login
```bash
curl -X POST http://localhost/login \
  -d "usuario=admin&senha=123456"
```

### Ver Produtos
```bash
curl http://localhost/cadastro-produtos
```

### Buscar Produto
```bash
curl http://localhost/cadastro-produtos/buscar?nome=Café
```

### Gestão de Estoque
```bash
curl http://localhost/gestao-estoque
```

## 📋 Funcionalidades Implementadas

✅ **Login/Logout**
- Autenticação de usuários
- Gerenciamento de sessão

✅ **Painel Principal**
- Dashboard com estatísticas
- Alertas de estoque mínimo
- Alertas de vencimento próximo

✅ **Cadastro de Produtos**
- Criar novo produto
- Listar produtos
- Buscar por nome
- Editar produto
- Deletar produto

✅ **Gestão de Estoque**
- Registrar entrada de produtos
- Registrar saída de produtos
- Listar produtos em ordem alfabética
- Buscar produtos
- Ver histórico de movimentações
- Estatísticas de estoque

## ⚠️ Problemas Comuns

### Erro: Database does not exist
**Solução:** Adicione `?createDatabaseIfNotExist=true` na URL de conexão

### Erro: Port 80 already in use
**Solução:** Mude a porta em `application.properties`:
```properties
server.port=8080
```

### Erro: Access denied for user 'root'
**Solução:** Verifique a senha do MySQL em `application.properties`

### Dados não aparecem na primeira execução
**Solução:** A classe `DataInitializer` popula os dados na inicialização. Aguarde a execução completa.

## 🔐 Segurança

### Dados de Teste
- ⚠️ **NÃO USE EM PRODUÇÃO!**
- Senhas estão em texto plano
- Implemente BCrypt para produção

### Recomendações
- Alterar senhas dos usuários de teste
- Implementar Spring Security
- Usar HTTPS em produção
- Implementar proteção CSRF
- Validar entrada de dados

## 📱 Próximas Melhorias

- [ ] Implementar Spring Security
- [ ] Criptografar senhas com BCrypt
- [ ] Adicionar paginação
- [ ] Implementar filtros avançados
- [ ] Adicionar validações com Bean Validation
- [ ] Gerar relatórios em PDF
- [ ] Implementar API REST
- [ ] Adicionar testes unitários

## 📞 Suporte

Para dúvidas sobre a implementação:
1. Consulte o arquivo `ETAPA_3.md`
2. Verifique os arquivos Javadoc nos controllers
3. Revise os comentários no código

## 📄 Documentação

- `ETAPA_3.md` - Documentação completa da Etapa 3
- `HELP.md` - Ajuda geral do projeto
- Comentários inline nos arquivos Java

---

**Última Atualização:** 22/05/2026  
**Status:** ✅ Pronto para Produção (com melhorias de segurança)

