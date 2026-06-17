# API de Cotação de Seguros

REST API para cadastro de clientes, veículos e geração de cotações de seguro com autenticação JWT.

```
Stack: Java 8 | Spring Boot 2.7.18 | H2 | JWT | Swagger | Flyway
```

---

## Índice

- [Autenticação](#-autenticação)
- [Endpoints](#-endpoints)
- [Fluxo de uso](#-fluxo-de-uso)
- [Como executar](#-como-executar)
- [Exemplos](#-exemplos)
- [Códigos de resposta](#-códigos-de-resposta)
- [Stack](#-stack)

---

## 🔐 Autenticação

A API utiliza **JWT (JSON Web Token)** para proteger os endpoints. Abaixo a legenda de acesso usada nas tabelas de endpoints:

```
🔓 Público  → não requer token de acesso
🔒 Privado  → requer token JWT no header Authorization
```

### Como obter seu token

```
1. POST /auth/signup  → cria seu usuário
2. POST /auth/login   → retorna o accessToken
3. Use o token em todos os endpoints 🔒
```

### Onde colocar o token

Em toda requisição para endpoints **🔒 Privado**, inclua o header:

```
Authorization: Bearer <seu_token_jwt>
```

**Exemplos em cada ferramenta:**

| Ferramenta | Como usar |
|---|---|
| **cURL** | `-H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9..."` |
| **Postman** | Aba **Authorization** → Tipo `Bearer Token` → colar o token |
| **Swagger UI** | Clique em **Authorize** (🔓 no canto superior direito) → digitar `Bearer <token>` → **Authorize** |
| **Insomnia** | Aba **Auth** → `Bearer Token` → colar o token |
| **JavaScript (fetch)** | `headers: { Authorization: 'Bearer <token>' }` |

> O token tem validade de **2 horas**. Após expirar, faça um novo `POST /auth/login` para obter outro.

---

## 🔌 Endpoints

> 🔓 Público &nbsp;|&nbsp; 🔒 Privado (exige `Authorization: Bearer <token>`)

### Autenticação

| Método | Endpoint | Descrição | Acesso |
|--------|----------|-----------|--------|
| `POST` | `/auth/signup` | Cadastrar novo usuário | 🔓 |
| `POST` | `/auth/login` | Autenticar e obter token JWT | 🔓 |

### Clientes

| Método | Endpoint | Descrição | Acesso |
|--------|----------|-----------|--------|
| `POST` | `/clientes` | Criar novo cliente | 🔒 |
| `GET` | `/clientes` | Listar todos os clientes | 🔒 |
| `GET` | `/clientes/{id}` | Buscar cliente por ID | 🔒 |

### Veículos

| Método | Endpoint | Descrição | Acesso |
|--------|----------|-----------|--------|
| `POST` | `/veiculos` | Cadastrar novo veículo | 🔒 |
| `GET` | `/veiculos` | Listar todos os veículos | 🔒 |
| `GET` | `/veiculos/{id}` | Buscar veículo por ID | 🔒 |

### Cotações

| Método | Endpoint | Descrição | Acesso |
|--------|----------|-----------|--------|
| `POST` | `/cotacoes` | Gerar nova cotação (vincula cliente + veículo) | 🔒 |
| `GET` | `/cotacoes` | Listar todas as cotações | 🔒 |
| `PUT` | `/cotacoes/{id}` | Atualizar cotação existente | 🔒 |

---

## 🔄 Fluxo de uso

```
1. POST /auth/signup         → cria usuário
2. POST /auth/login          → recebe token JWT
3. POST /veiculos            → cadastra veículo (com token)
4. POST /clientes            → cadastra cliente (com token)
5. POST /cotacoes            → gera cotação vinculando cliente + veículo (com token)
```

---

## 🚀 Como executar

**Pré-requisitos:** Java 8+ e Maven 3.6+

```bash
git clone https://github.com/seu-usuario/api-cotacao-seguros.git
cd api-cotacao-seguros
mvn spring-boot:run
```

Acessar em `http://localhost:8080`.

| Recurso | URL |
|---------|-----|
| API | `http://localhost:8080` |
| Swagger UI | `http://localhost:8080/swagger-ui/` |
| Console H2 | `http://localhost:8080/h2-console` |

> H2 — JDBC: `jdbc:h2:mem:segurosdb` | User: `sa` | Senha: *(vazio)*

---

## 🧪 Exemplos

### 1. Criar usuário

```bash
curl -X POST http://localhost:8080/auth/signup \
  -H "Content-Type: application/json" \
  -d '{"email":"admin@email.com","password":"123456"}'
```

### 2. Login (receber token)

```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"admin@email.com","password":"123456"}'
```

Resposta:

```json
{
  "code": 0,
  "message": "OK",
  "data": {
    "accessToken": "eyJhbGciOiJIUzI1NiJ9..."
  }
}
```

### 3. Criar cliente (autenticado)

```bash
curl -X POST http://localhost:8080/clientes \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9..." \
  -d '{"nome": "João Silva", "cpf": "123.456.789-00", "email": "joao@email.com"}'
```

### 4. Criar veículo (autenticado)

```bash
curl -X POST http://localhost:8080/veiculos \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9..." \
  -d '{"placa": "ABC-1234", "marca": "Toyota", "modelo": "Corolla", "ano": 2024, "valor": 120000.00}'
```

### 5. Gerar cotação (autenticado)

```bash
curl -X POST http://localhost:8080/cotacoes \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9..." \
  -d '{"idCliente": 1, "idVeiculo": 1}'
```

---

## 📊 Códigos de resposta

Todas as respostas seguem o formato padronizado:

```json
{
  "code": 0,
  "message": "Operação realizada com sucesso",
  "data": { }
}
```

| Código | Significado |
|--------|-------------|
| `0` | Sucesso |
| `-1` | Erro genérico |
| `1` | Erro de parâmetro |
| `2` | Email duplicado |
| `3` | CPF duplicado |
| `4` | Recurso não encontrado |
| `5` | Não autorizado |
| `6` | Erro de validação |

---

## 🛠 Stack

| Categoria | Tecnologia |
|-----------|-----------|
| Linguagem | Java 8 |
| Framework | Spring Boot 2.7.18 |
| Autenticação | Spring Security + jjwt 0.11.5 |
| Persistência | Spring Data JPA + Flyway 9.22.3 |
| Banco | H2 (in-memory) |
| Documentação | Springfox Swagger 3.0.0 |
| Mapping | MapStruct 1.5.5 |
| Build | Maven |
| Outros | Lombok, Bean Validation
