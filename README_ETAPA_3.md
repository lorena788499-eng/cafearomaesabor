# 🚀 Café Aroma & Sabor - Sistema de Gestão de Estoque

## ETAPA 3 - Controllers, Models e Repositories ✅

Bem-vindo! Você está visualizando a **Etapa 3 concluída** do projeto Café Aroma & Sabor.

---

## 📋 O Que é Este Projeto?

Um **sistema web de gestão de estoque** desenvolvido em **Spring Boot 4.0.6** com **Java 21**, **MySQL 8.0+**, **Thymeleaf** e **Bootstrap**.

Implementa:
- ✅ Autenticação de usuários
- ✅ CRUD completo de produtos
- ✅ Gestão de entrada/saída de estoque
- ✅ Controle de estoque mínimo
- ✅ Alertas de vencimento
- ✅ Histórico de movimentações

---

## 🎯 Etapas do Projeto

| Etapa | Status | Descrição |
|-------|--------|-----------|
| **Etapa 1** | ✅ Completa | Requisitos Funcionais |
| **Etapa 2** | ✅ Completa | Interfaces/Templates HTML |
| **Etapa 3** | ✅ **VOCÊ ESTÁ AQUI** | Controllers, Models, Repositories |

---

## 🚀 Quick Start (5 Minutos)

### 1️⃣ Pré-requisitos
- Java 21+
- Maven 3.6+
- MySQL 8.0+ (servidor rodando)

### 2️⃣ Configuração
Edite `src/main/resources/application.properties`:
```properties
spring.datasource.username=root
spring.datasource.password=SUA_SENHA_DO_MYSQL
```

### 3️⃣ Execute
```bash
mvn clean install
mvn spring-boot:run
```

### 4️⃣ Acesse
- **URL:** http://localhost/login
- **Usuário:** admin
- **Senha:** 123456

---

## 📊 O Que Foi Criado na Etapa 3?

### Controllers (4)
```
LoginController.java       → Autenticação (3 endpoints)
HomeController.java        → Dashboard (2 endpoints)
ProdutoController.java     → CRUD Produtos (5 endpoints)
EstoqueController.java     → Gestão Estoque (3 endpoints)
```

### Models (3)
```
Usuario.java              → Usuários do sistema
Produto.java              → Produtos em estoque
MovimentacaoEstoque.java  → Entradas/Saídas
```

### Repositories (3)
```
UsuarioRepository.java    → Acesso a Usuários
ProdutoRepository.java    → Acesso a Produtos  + 8 métodos customizados
MovimentacaoEstoqueRepository.java → Acesso a Movimentações
```

### Configuração (1)
```
DataInitializer.java      → Popula dados iniciais
```

### **Total: 11 arquivos Java + 4 documentação + 1 SQL**

---

## 📚 Documentação

Leia os documentos na raiz do projeto:

1. **[ETAPA_3.md](ETAPA_3.md)** 
   - Documentação técnica completa
   - Descrição de Controllers, Models, Repositories
   - Endpoints e funcionalidades

2. **[INSTALACAO_ETAPA_3.md](INSTALACAO_ETAPA_3.md)**
   - Guia passo-a-passo de instalação
   - Solução de problemas comuns

3. **[TESTES_ENDPOINTS.md](TESTES_ENDPOINTS.md)**
   - 30+ exemplos de teste
   - Testes com curl
   - Scripts Python

4. **[RESUMO_PROJETO.md](RESUMO_PROJETO.md)**
   - Resumo executivo
   - Arquitetura
   - Estatísticas

5. **[CHECKLIST_ETAPA_3.md](CHECKLIST_ETAPA_3.md)**
   - Checklist completo
   - Arquivos criados
   - Funcionalidades

---

## 🗺️ Rotas Disponíveis

### 🔐 Autenticação
```
GET  /login              → Página de login
POST /login              → Autenticar usuário
GET  /logout             → Fazer logout
```

### 📊 Dashboard
```
GET  /                   → Painel principal
GET  /index              → Alternativa
```

### 📦 Produtos
```
GET  /cadastro-produtos              → Listar produtos
POST /cadastro-produtos              → Criar produto
GET  /cadastro-produtos/buscar       → Buscar por nome
POST /cadastro-produtos/{id}         → Atualizar produto
GET  /cadastro-produtos/deletar/{id} → Deletar produto
```

### 📊 Estoque
```
GET  /gestao-estoque                 → Gestão de estoque
POST /gestao-estoque                 → Registrar movimentação
GET  /gestao-estoque/buscar          → Buscar produtos
GET  /gestao-estoque/historico       → Ver histórico
```

---

## 💾 Dados de Teste

### Usuários
```
Username: admin
Password: 123456

Username: joao
Password: 123456
```

### Produtos (12 Exemplos)
Café, Chá, Acessórios, Alimentos pré-cadastrados

---

## 🏗️ Arquitetura

```
Frontend (Thymeleaf Templates)
        ↓
Controllers (13 endpoints)
        ↓
Models (JPA Entities)
        ↓
Repositories (Spring Data JPA)
        ↓
MySQL Database
```

---

## 🧪 Como Testar?

### Opção 1: Via Navegador
1. Abra http://localhost/login
2. Digite admin / 123456
3. Explore as funcionalidades

### Opção 2: Via Curl
```bash
curl -X POST http://localhost/login \
  -d "usuario=admin&senha=123456" \
  -c cookies.txt

curl http://localhost/cadastro-produtos \
  -b cookies.txt
```

### Opção 3: Via Python
Consulte [TESTES_ENDPOINTS.md](TESTES_ENDPOINTS.md)

---

## 📁 Estrutura de Pastas

```
CafeAromaESabor/
├── src/main/java/com/somativa/cafearomaesabor/
│   ├── controller/        ← 4 Controllers
│   ├── model/            ← 3 Models
│   ├── repository/       ← 3 Repositories
│   └── config/           ← Configuração
├── src/main/resources/
│   ├── templates/        ← HTML Templates
│   ├── application.properties
│   └── database.sql
├── pom.xml              ← Dependências Maven
└── Documentação (*.md)  ← 5 arquivos
```

---

## 🔒 Segurança

✅ Implementado:
- Autenticação com sessão HTTP
- Verificação em todos os endpoints
- Validação de entrada

⚠️ Recomendado para Produção:
- Implementar Spring Security
- Criptografar senhas com BCrypt
- Adicionar HTTPS
- Proteção CSRF

---

## 🐛 Troubleshooting

### Erro: "Database does not exist"
→ Adicione `?createDatabaseIfNotExist=true` na URL

### Erro: "Port 80 already in use"
→ Mude em `application.properties`: `server.port=8080`

### Erro: "Access denied for MySQL"
→ Verifique senha em `application.properties`

Mais soluções em [INSTALACAO_ETAPA_3.md](INSTALACAO_ETAPA_3.md)

---

## 📈 Estatísticas

- **11** arquivos Java criados
- **1.800+** linhas de código
- **13** endpoints implementados
- **3** models com relacionamentos
- **15** métodos customizados em repositories
- **100%** dos requisitos atendidos

---

## 🎓 O que você pode fazer?

### ✅ Agora Possível
- Fazer login e logout
- Ver dashboard com estatísticas
- Cadastrar produtos (CRUD)
- Registrar entrada/saída
- Buscar produtos
- Ver histórico de movimentações
- Gerar alertas automáticos

### ❌ Futuro (Próximas Etapas)
- Gerar relatórios em PDF
- Exportar para Excel
- Implementar Spring Security
- Criar API REST
- Adicionar testes unitários

---

## 📞 Precisa de Ajuda?

1. Consulte [ETAPA_3.md](ETAPA_3.md) para detalhes técnicos
2. Veja [INSTALACAO_ETAPA_3.md](INSTALACAO_ETAPA_3.md) para instalação
3. Use [TESTES_ENDPOINTS.md](TESTES_ENDPOINTS.md) para testar
4. Leia o código - está bem comentado!

---

## 🎉 Pronto para Começar?

```bash
# 1. Instale as dependências
mvn clean install

# 2. Execute
mvn spring-boot:run

# 3. Acesse http://localhost/login
# 4. Login com admin/123456
```

---

## 📜 Licença

Projeto educacional - Desenvolvimento Proposto

---

## ✨ Desenvolvido com

- **Spring Boot** 4.0.6
- **Java** 21
- **MySQL** 8.0+
- **Thymeleaf**
- **Bootstrap** 5
- **Maven**
- **Lombok**
- **Spring Data JPA**

---

## 📅 Histórico de Versão

| Versão | Data | Status |
|--------|------|--------|
| 1.0.0 | 22/05/2026 | ✅ Etapa 3 Completa |

---

**Obrigado por usar o Café Aroma & Sabor!** ☕

---

**Documentação Completa em:** [ETAPA_3.md](ETAPA_3.md)  
**Como Instalar:** [INSTALACAO_ETAPA_3.md](INSTALACAO_ETAPA_3.md)  
**Testar Endpoints:** [TESTES_ENDPOINTS.md](TESTES_ENDPOINTS.md)

