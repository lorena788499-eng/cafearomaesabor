# Café Aroma e Sabor - Sistema de Gestão de Estoque

## 📋 Sobre o Projeto

O **Café Aroma e Sabor** é um sistema web de **gestão de estoque** desenvolvido em **Spring Boot 4.0.6** com **Thymeleaf** e **Spring Data JPA**, projetado para controlar produtos, movimentações de entrada/saída, validar datas de vencimento e gerenciar níveis mínimos de estoque.

---

## 🎯 Funcionalidades Principais

### 1. **Autenticação e Controle de Acesso**
- Login com usuário e senha
- Dois perfis de usuário: **ADMIN** e **USER**
- Usuários em memória (padrão) ou integrados ao banco de dados
- Sessão de usuário com logout seguro

### 2. **Cadastro de Produtos** (CRUD Completo)
- Criar novo produto
- Listar produtos cadastrados
- Buscar produto por nome
- Atualizar informações do produto
- Deletar produto do estoque
- Campos: Código, Nome, Descrição, Categoria, Quantidade, Estoque Mínimo, Lote, Data Validade

### 3. **Gestão de Estoque**
- Registrar entrada de produtos
- Registrar saída de produtos
- Atualizar dinamicamente a quantidade em estoque
- Listar movimentações em ordem alfabética
- Histórico de movimentações (tipo, quantidade, data)

### 4. **Controle de Validade**
- Monitorar datas de vencimento dos produtos
- Emitir alertas para produtos próximos ao vencimento
- Consultar produtos por data de validade

### 5. **Controle de Estoque Mínimo**
- Definir limite mínimo por produto
- Alertas automáticos quando atinge o mínimo
- Visualização de produtos em baixo estoque

### 6. **Dashboard**
- Visualizar estatísticas gerais do sistema
- Quantidade total de produtos
- Movimentações recentes
- Nome do usuário logado

---

## 🚀 Quick Start - Início Rápido

### Pré-requisitos
- Java 17+
- Maven 3.8+
- Banco de dados MySQL (ou H2 em-memória)

### Passos de Instalação

1. **Clone ou extraia o projeto:**
```bash
cd C:\Users\49095983838\IdeaProjects\CafeAromaESabor
```

2. **Configure o banco de dados** (arquivo `application.properties`):
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/cafearoma
spring.datasource.username=root
spring.datasource.password=seu_password
spring.jpa.hibernate.ddl-auto=update
```

3. **Build do projeto:**
```bash
.\mvnw.cmd clean package -DskipTests
```

4. **Execute a aplicação:**
```bash
.\mvnw.cmd spring-boot:run
```

5. **Acesse no navegador:**
```
http://localhost:8080/login
```

---

## 👤 Usuários de Teste (In-Memory)

| Usuário | Senha | Papel |
|---------|-------|-------|
| `admin`  | `123456` | ADMIN |
| `joao`   | `123456` | USER |

---

## 🗺️ Rotas e Endpoints Principais

### Autenticação
- `GET  /login` - Exibe página de login
- `POST /login` - Processa login (Spring Security)
- `GET  /logout` - Realiza logout

### Dashboard
- `GET  /` - Dashboard principal (requer autenticação)
- `GET  /index` - Redirecionamento para dashboard

### Produtos
- `GET  /cadastro-produtos` - Listar todos os produtos
- `GET  /cadastro-produtos/novo` - Formulário para novo produto
- `POST /cadastro-produtos` - Salvar novo produto
- `GET  /cadastro-produtos/{id}/editar` - Formulário para editar
- `POST /cadastro-produtos/{id}` - Atualizar produto
- `GET  /cadastro-produtos/{id}/deletar` - Deletar produto
- `GET  /cadastro-produtos/buscar` - Buscar por nome

### Gestão de Estoque
- `GET  /gestao-estoque` - Interface de movimentação
- `POST /gestao-estoque` - Registrar entrada/saída
- `GET  /gestao-estoque/listar` - Listar histórico
- `GET  /gestao-estoque/buscar` - Buscar movimentação

---

## 🏗️ Arquitetura e Estrutura

```
src/main/java/com/somativa/cafearomaesabor/
├── config/
│   ├── SecurityConfig.java          # Configuração de segurança
│   └── DataInitializer.java         # Carregamento de dados iniciais
├── controller/
│   ├── LoginController.java         # Autenticação
│   ├── HomeController.java          # Dashboard
│   ├── ProdutoController.java       # CRUD de produtos
│   └── EstoqueController.java       # Movimentação de estoque
├── model/
│   ├── Usuario.java                 # Entidade de usuário
│   ├── Produto.java                 # Entidade de produto
│   └── MovimentacaoEstoque.java    # Entidade de movimentação
├── repository/
│   ├── UsuarioRepository.java       # Acesso a usuários
│   ├── ProdutoRepository.java       # Acesso a produtos
│   └── MovimentacaoEstoqueRepository.java  # Acesso a movimentações
└── CafeAromaESaborApplication.java  # Classe principal

src/main/resources/
├── application.properties           # Configurações da aplicação
├── database.sql                     # Script SQL de inicialização
├── static/
│   └── css/
│       └── style.css                # Estilos personalizados
└── templates/
    ├── index.html                   # Dashboard
    ├── login.html                   # Login
    ├── cadastro-produtos.html       # Gerenciamento de produtos
    └── gestao-estoque.html          # Gestão de estoque
```

---

## 🔐 Segurança

- **Autenticação:** Spring Security com usuários em memória
- **Senhas:** Criptografadas com BCrypt
- **Autorização:** Baseada em roles (ADMIN, USER)
- **Sessão:** Gerenciada automaticamente pelo Spring Security
- **CSRF:** Configurado para ambiente de produção

---

## 🧪 Testando Endpoint (Exemplos)

### Login via cURL
```bash
curl -X POST "http://localhost:8080/login" \
  -d "username=admin&password=123456" \
  -c cookies.txt
```

### Listar Produtos
```bash
curl -X GET "http://localhost:8080/cadastro-produtos" \
  -b cookies.txt
```

### Cadastrar Novo Produto
```bash
curl -X POST "http://localhost:8080/cadastro-produtos" \
  -d "nome=Café Premium&descricao=Café especial&preco=25.50&categoria=Premium" \
  -b cookies.txt
```

---

## 📚 Documentação de Referência

### Spring Boot & Maven
* [Apache Maven Documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference](https://docs.spring.io/spring-boot/4.0.6/maven-plugin)
* [Spring Boot Documentation](https://docs.spring.io/spring-boot/4.0.6/reference/)

### Componentes Utilizados
* [Spring Data JPA](https://docs.spring.io/spring-boot/4.0.6/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Thymeleaf Template Engine](https://docs.spring.io/spring-boot/4.0.6/reference/web/servlet.html#web.servlet.spring-mvc.template-engines)
* [Spring Security](https://docs.spring.io/spring-security/reference/)
* [Form Submission Handling](https://spring.io/guides/gs/handling-form-submission/)
* [Spring Web MVC](https://docs.spring.io/spring-boot/4.0.6/reference/web/servlet.html)

### Recursos Adicionais
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing Data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)
* [Building REST Services](https://spring.io/guides/tutorials/rest/)

---

## 🛠️ Troubleshooting

### Erro de conexão com banco de dados
- Verifique se MySQL está rodando
- Confirme credenciais em `application.properties`
- Execute o script `database.sql` manualmente

### Login não funciona
- Verifique se você está usando `admin/123456` ou `joao/123456`
- Certifique-se de que o Spring Security está configurado corretamente
- Limpe cookies do navegador e tente novamente

### Templates não aparecem
- Verifique se os arquivos HTML estão em `src/main/resources/templates/`
- Reinicie a aplicação
- Limpe a cache do navegador (Ctrl+F5)

---

## 📁 Arquivos de Documentação do Projeto

- **START.md** - Comece aqui! Guia rápido de 3 passos
- **README_ETAPA_3.md** - Overview técnico da etapa 3
- **ETAPA_3.md** - Documentação detalhada da implementação
- **INSTALACAO_ETAPA_3.md** - Guia passo-a-passo de instalação
- **TESTES_ENDPOINTS.md** - Exemplos de teste de todos os endpoints
- **MAPA_DO_PROJETO.md** - Diagrama e estrutura do projeto
- **CHECKLIST_ETAPA_3.md** - Checklist de requisitos entregues

---

## 📞 Suporte e Contribuição

Para dúvidas ou sugestões sobre o projeto, consulte a documentação completa disponível na raiz do projeto.
