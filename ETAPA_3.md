# Etapa 3 - Controllers, Models e Repositories

## Descrição
A Etapa 3 implementa os Controllers, Models (Entidades JPA) e Repositories necessários para o sistema de gestão de estoque Café Aroma & Sabor, completando a camada server-side do projeto.

## Arquivos Criados

### 📦 Models (Entidades JPA)

#### 1. **Usuario.java** (`src/main/java/com/somativa/cafearomaesabor/model/`)
- Entidade para armazenar informações dos usuários do sistema
- Campos: id, nomeUsuario, senha, nomeCompleto, email, ativo, dataCriacao, dataAtualizacao
- Tabela: `usuarios`

#### 2. **Produto.java** (`src/main/java/com/somativa/cafearomaesabor/model/`)
- Entidade para armazenar produtos do estoque
- Campos: id, codigo, nome, descricao, categoria, quantidade, estoqueMinimo, lote, dataValidade, dataCriacao, dataAtualizacao
- Tabela: `produtos`
- Métodos utilitários: isEstoqueMinimo(), isEstoqueCritico(), isProximoVencimento(), isVencido()

#### 3. **MovimentacaoEstoque.java** (`src/main/java/com/somativa/cafearomaesabor/model/`)
- Entidade para registrar entrada e saída de produtos
- Campos: id, produto, usuario, tipo, quantidade, dataMovimentacao, observacoes, dataCriacao
- Tabela: `movimentacoes_estoque`
- Enum: TipoMovimentacao (ENTRADA, SAIDA)

### 📊 Repositories (Camada de Acesso a Dados)

#### 1. **UsuarioRepository.java**
- Métodos: findByNomeUsuario(), findByEmail()

#### 2. **ProdutoRepository.java**
- Métodos customizados:
  - findByCodigo() - busca por código
  - findByNomeContainingIgnoreCase() - busca por nome
  - findByOrderByNomeAsc() - lista ordenada por nome
  - findByQuantidadeLessThanOrEqual() - produtos abaixo de quantidade
  - findByDataValidadeBeforeOrderByDataValidadeAsc() - produtos vencidos
  - findProdutosEmAlerta() - produtos com estoque em alerta
  - findProdutosVencidos() - produtos vencidos
  - findProdutosProximosVencimento() - produtos vencendo em 7 dias

#### 3. **MovimentacaoEstoqueRepository.java**
- Métodos: findByProduto(), findByDataMovimentacaoBetween(), findTop10ByOrderByDataMovimentacaoDescIdDesc()

### 🎮 Controllers

#### 1. **LoginController.java**
**Rotas:**
- `GET /login` - Exibe página de login
- `POST /login` - Processa autenticação de usuário
- `GET /logout` - Realiza logout do usuário

**Funcionalidades:**
- Validação de credenciais
- Gerenciamento de sessão HTTP
- Redirecionamento de usuários não autenticados

#### 2. **HomeController.java**
**Rotas:**
- `GET /` - Exibe painel principal (Dashboard)
- `GET /index` - Alternativa para o painel principal

**Funcionalidades:**
- Verificação de autenticação
- Cálculo de estatísticas do estoque
- Passagem de dados para a view usando Thymeleaf

#### 3. **ProdutoController.java**
**Rotas:**
- `GET /cadastro-produtos` - Exibe página de cadastro e lista de produtos
- `POST /cadastro-produtos` - Cadastra novo produto
- `GET /cadastro-produtos/buscar?nome=` - Busca produtos por nome
- `POST /cadastro-produtos/{id}` - Atualiza produto existente
- `GET /cadastro-produtos/deletar/{id}` - Deleta um produto

**Funcionalidades:**
- CRUD completo de produtos
- Validação de códigos duplos
- Busca por nome do produto

#### 4. **EstoqueController.java**
**Rotas:**
- `GET /gestao-estoque` - Exibe página de gestão de estoque
- `POST /gestao-estoque` - Registra entrada ou saída de produtos
- `GET /gestao-estoque/buscar?termo=` - Busca produtos por código ou nome
- `GET /gestao-estoque/historico` - Exibe histórico de movimentações

**Funcionalidades:**
- Registro de movimentações (entrada/saída)
- Atualização automática de quantidade
- Controle de estoque mínimo
- Histórico de movimentações
- Cálculo de estatísticas

### ⚙️ Configuração

#### DataInitializer.java
- Popula o banco com dados iniciais
- Cria 2 usuários de teste
- Cria 12 produtos de exemplo
- Executa automaticamente na inicialização da aplicação

**Usuários de teste:**
- Username: `admin` | Senha: `123456`
- Username: `joao` | Senha: `123456`

## 🚀 Como Usar

### 1. Configurar o Banco de Dados
Edite o arquivo `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/CafeAromaESabor?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=Senai@403
```

### 2. Executar a Aplicação
```bash
mvn clean install
mvn spring-boot:run
```

Ou execute a classe `CafeAromaESaborApplication.java`

### 3. Acessar o Sistema
- URL: `http://localhost/login`
- Credenciais de teste:
  - Username: `admin`
  - Senha: `123456`

## 📋 Fluxo das Páginas com Controllers

```
Login (GET /login)
    ↓ POST /login (validar credenciais)
    ↓
Painel Principal (GET /)
    ├── → Cadastro Produtos (GET /cadastro-produtos)
    │   ├─ POST → Novo Produto
    │   ├─ GET → Buscar Produto (/buscar)
    │   └─ GET → Deletar Produto (/deletar/{id})
    │
    └── → Gestão Estoque (GET /gestao-estoque)
        ├─ POST → Registrar Movimentação
        ├─ GET → Buscar Produto (/buscar)
        └─ GET → Ver Histórico (/historico)

Logout (GET /logout)
    ↓
Login
```

## 🔒 Segurança

### Implementado
- Verificação de autenticação em todos os Controllers
- Sessão HTTP para manter usuário logado
- Redirect automático para login se não autenticado

### Recomendações Futuras
- Implementar criptografia BCrypt para senhas
- Adicionar Spring Security para autenticação mais robusta
- Implementar autorização por roles (ADMIN, USER, etc.)
- Adicionar proteção CSRF
- Implementar rate limiting para login

## 📝 Endpoints Resumo

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/login` | Página de login |
| POST | `/login` | Processar login |
| GET | `/logout` | Logout |
| GET | `/` | Painel principal |
| GET | `/cadastro-produtos` | Listar produtos |
| POST | `/cadastro-produtos` | Cadastrar produto |
| GET | `/cadastro-produtos/buscar` | Buscar produto |
| POST | `/cadastro-produtos/{id}` | Atualizar produto |
| GET | `/cadastro-produtos/deletar/{id}` | Deletar produto |
| GET | `/gestao-estoque` | Gestão de estoque |
| POST | `/gestao-estoque` | Registrar movimentação |
| GET | `/gestao-estoque/buscar` | Buscar em estoque |
| GET | `/gestao-estoque/historico` | Ver histórico |

## 🛠️ Dependências Adicionadas

- Spring Security Crypto (para criptografia de senhas)
- Lombok (para reduzir código boilerplate)
- MySQL Connector
- Spring Data JPA
- Thymeleaf
- Spring Web MVC

## 📚 Relacionamentos entre Entidades

```
Usuario (1) ───→ (N) MovimentacaoEstoque
Produto (1) ───→ (N) MovimentacaoEstoque
```

## 🐛 Tratamento de Erros

Todos os Controllers implementam:
- Try-catch para operações de banco de dados
- Validação de entrada de dados
- Mensagens de erro amigáveis
- Redirecionamento automático para login se sessão expirar

## 📱 Próximos Passos (Etapa 4)

- Implementar telas de edição de produtos
- Adicionar paginação nas listagens
- Implementar filtros avançados
- Melhorar validações com Bean Validation
- Adicionar autenticação com Spring Security
- Implementar relatórios em PDF

---

**Versão:** 1.0.0  
**Data:** 22/05/2026  
**Status:** ✅ Concluído

