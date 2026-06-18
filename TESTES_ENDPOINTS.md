# Testes dos Endpoints - Etapa 3

## 🧪 Teste de Login

### 1. Acessar página de login
```
GET http://localhost/login
```
**Resposta esperada:** Página HTML de login

### 2. Fazer login com credenciais válidas
```bash
curl -X POST http://localhost/login \
  -d "usuario=admin&senha=123456" \
  -c cookies.txt
```
**Resposta esperada:** Redirecionamento para `/` (painel principal)

### 3. Fazer login com credenciais inválidas
```bash
curl -X POST http://localhost/login \
  -d "usuario=admin&senha=errado" \
  -c cookies.txt
```
**Resposta esperada:** Página de login com mensagem de erro

### 4. Fazer logout
```bash
curl -X GET http://localhost/logout \
  -b cookies.txt
```
**Resposta esperada:** Redirecionamento para `/login`

---

## 📊 Testes do Painel Principal

### 1. Acessar painel principal (logado)
```bash
curl -X GET http://localhost/ \
  -b cookies.txt
```
**Resposta esperada:** Página index.html com dados do dashboard

### 2. Tentar acessar painel sem estar logado
```bash
curl -X GET http://localhost/
```
**Resposta esperada:** Redirecionamento para `/login`

---

## 📦 Testes de Cadastro de Produtos

### 1. Listar todos os produtos
```bash
curl -X GET http://localhost/cadastro-produtos \
  -b cookies.txt
```
**Resposta esperada:** Página com lista de produtos existentes

### 2. Cadastrar novo produto
```bash
curl -X POST http://localhost/cadastro-produtos \
  -d "codigo=NOVO001" \
  -d "nome=Novo Produto" \
  -d "descricao=Descrição do novo produto" \
  -d "categoria=cafe" \
  -d "quantidade=50" \
  -d "estoque_minimo=20" \
  -d "lote=L99999" \
  -d "data_validade=2025-12-31" \
  -b cookies.txt
```
**Resposta esperada:** Redirecionamento de volta para `/cadastro-produtos` com mensagem de sucesso

### 3. Buscar produto por nome
```bash
curl -X GET "http://localhost/cadastro-produtos/buscar?nome=Café%20Arábica" \
  -b cookies.txt
```
**Resposta esperada:** Lista filtrada de produtos contendo "Café Arábica"

### 4. Atualizar um produto
```bash
curl -X POST http://localhost/cadastro-produtos/1 \
  -d "nome=Café Arábica Premium Atualizado" \
  -d "descricao=Descrição atualizada" \
  -d "categoria=cafe" \
  -d "estoque_minimo=25" \
  -d "lote=L12345" \
  -d "data_validade=2025-12-25" \
  -b cookies.txt
```
**Resposta esperada:** Mensagem de sucesso na página de cadastro

### 5. Deletar um produto
```bash
curl -X GET http://localhost/cadastro-produtos/deletar/1 \
  -b cookies.txt
```
**Resposta esperada:** Mensagem de sucesso na página de cadastro

---

## 📊 Testes de Gestão de Estoque

### 1. Acessar página de gestão de estoque
```bash
curl -X GET http://localhost/gestao-estoque \
  -b cookies.txt
```
**Resposta esperada:** Página com formulário de movimentação e tabela de produtos

### 2. Registrar entrada de produto
```bash
curl -X POST http://localhost/gestao-estoque \
  -d "produto=1" \
  -d "tipo_movimentacao=ENTRADA" \
  -d "quantidade=15" \
  -d "data_movimentacao=2026-05-22" \
  -d "observacoes=Compra fornecedor A" \
  -b cookies.txt
```
**Resposta esperada:** Mensagem de sucesso e quantidade do produto atualizada

### 3. Registrar saída de produto
```bash
curl -X POST http://localhost/gestao-estoque \
  -d "produto=1" \
  -d "tipo_movimentacao=SAIDA" \
  -d "quantidade=5" \
  -d "data_movimentacao=2026-05-22" \
  -d "observacoes=Venda cliente X" \
  -b cookies.txt
```
**Resposta esperada:** Mensagem de sucesso e quantidade do produto atualizada

### 4. Buscar produto em estoque
```bash
curl -X GET "http://localhost/gestao-estoque/buscar?termo=Café%20Arábica" \
  -b cookies.txt
```
**Resposta esperada:** Página filtrada com o produto buscado

### 5. Ver histórico de movimentações
```bash
curl -X GET http://localhost/gestao-estoque/historico \
  -b cookies.txt
```
**Resposta esperada:** Página com as últimas 10 movimentações registradas

---

## 🧬 Teste com Verificação de Estoque Insuficiente

### Cenário: Tentar sair mais produtos do que tem em estoque
```bash
# Primeiro, verificar quantidade atual
curl -X GET http://localhost/gestao-estoque \
  -b cookies.txt

# Tentar sair com quantidade maior que o estoque
curl -X POST http://localhost/gestao-estoque \
  -d "produto=1" \
  -d "tipo_movimentacao=SAIDA" \
  -d "quantidade=1000" \
  -d "data_movimentacao=2026-05-22" \
  -b cookies.txt
```
**Resposta esperada:** Mensagem de erro "Quantidade em estoque insuficiente"

---

## 🔄 Teste de Validações

### 1. Código de produto duplicado
```bash
curl -X POST http://localhost/cadastro-produtos \
  -d "codigo=CAF001" \
  -d "nome=Outro Café" \
  -d "descricao=Descrição" \
  -d "categoria=cafe" \
  -d "quantidade=50" \
  -d "estoque_minimo=20" \
  -d "lote=L12345" \
  -d "data_validade=2025-12-31" \
  -b cookies.txt
```
**Resposta esperada:** Mensagem de erro "Código do produto já existe"

### 2. Quantidade negativa em movimentação
```bash
curl -X POST http://localhost/gestao-estoque \
  -d "produto=1" \
  -d "tipo_movimentacao=ENTRADA" \
  -d "quantidade=0" \
  -d "data_movimentacao=2026-05-22" \
  -b cookies.txt
```
**Resposta esperada:** Mensagem de erro "Quantidade deve ser maior que zero"

### 3. Tipo de movimentação inválido
```bash
curl -X POST http://localhost/gestao-estoque \
  -d "produto=1" \
  -d "tipo_movimentacao=INVALIDO" \
  -d "quantidade=10" \
  -d "data_movimentacao=2026-05-22" \
  -b cookies.txt
```
**Resposta esperada:** Mensagem de erro "Tipo de movimentação inválido"

---

## 📊 Teste de Dados Dinâmicos no Dashboard

### Verificar se as estatísticas são calculadas corretamente
```bash
curl -X GET http://localhost/ \
  -b cookies.txt | grep -o "Total de Produtos\|Produtos Alerta\|Próximos a Vencer"
```

**Dados esperados no modelo:**
- `totalProdutos` - número total de produtos cadastrados
- `produtosAlerta` - produtos com quantidade ≤ estoque mínimo
- `produtosProximosVencer` - produtos com data de validade nos próximos 7 dias
- `movimentacoesHoje` - quantidade de movimentações recentes

---

## 🔐 Teste de Autenticação

### 1. Tentar acessar /cadastro-produtos sem estar logado
```bash
curl -X GET http://localhost/cadastro-produtos
```
**Resposta esperada:** Redirecionamento para `/login`

### 2. Tentar acessar /gestao-estoque sem estar logado
```bash
curl -X GET http://localhost/gestao-estoque
```
**Resposta esperada:** Redirecionamento para `/login`

### 3. Usar cookie com sessão expirada
```bash
curl -X GET http://localhost/cadastro-produtos \
  -b cookies_expirado.txt
```
**Resposta esperada:** Redirecionamento para `/login`

---

## 💾 Scriptando Testes com Python

```python
import requests

BASE_URL = "http://localhost"

# 1. Fazer login
session = requests.Session()
login_data = {
    "usuario": "admin",
    "senha": "123456"
}
response = session.post(f"{BASE_URL}/login", data=login_data)
print(f"Login: {response.status_code}")

# 2. Acessar painel principal
response = session.get(f"{BASE_URL}/")
print(f"Painel Principal: {response.status_code}")

# 3. Listar produtos
response = session.get(f"{BASE_URL}/cadastro-produtos")
print(f"Listar Produtos: {response.status_code}")

# 4. Registrar entrada
entrada_data = {
    "produto": "1",
    "tipo_movimentacao": "ENTRADA",
    "quantidade": "10",
    "data_movimentacao": "2026-05-22",
    "observacoes": "Teste via Python"
}
response = session.post(f"{BASE_URL}/gestao-estoque", data=entrada_data)
print(f"Registrar Entrada: {response.status_code}")

# 5. Ver gestão de estoque
response = session.get(f"{BASE_URL}/gestao-estoque")
print(f"Gestão de Estoque: {response.status_code}")

# 6. Fazer logout
response = session.get(f"{BASE_URL}/logout")
print(f"Logout: {response.status_code}")
```

---

## ✅ Checklist de Testes

- [ ] Login com credenciais válidas
- [ ] Login com credenciais inválidas
- [ ] Logout funciona
- [ ] Acesso negado sem autenticação
- [ ] Dashboard carrega com dados corretos
- [ ] Cadastrar novo produto
- [ ] Listar produtos
- [ ] Buscar produto por nome
- [ ] Atualizar produto
- [ ] Deletar produto
- [ ] Registrar entrada de produto
- [ ] Registrar saída de produto
- [ ] Validação de quantidade insuficiente
- [ ] Validação de código duplicado
- [ ] Histórico de movimentações funciona
- [ ] Alertas de estoque mínimo aparecem
- [ ] Alertas de vencimento próximo aparecem

---

**Versão:** 1.0.0  
**Data:** 22/05/2026  
**Status:** ✅ Testes Documentados

