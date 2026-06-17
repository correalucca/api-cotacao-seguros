# API de Cotação de Seguros

API REST para cadastro de clientes, veículos e geração de cotações de seguros, com autenticação JWT.

**Stack**: Java 8 + Spring Boot 2.7.18 + H2 + Springfox Swagger

---

## Endpoints

| Método | Endpoint | Descrição | Autenticação |
|--------|----------|-----------|-------------|
| `POST` | `/auth/signup` | Cadastrar usuário | ❌ |
| `POST` | `/auth/login` | Login (retorna token JWT) | ❌ |
| `POST` | `/clientes` | Criar cliente | ✅ |
| `GET` | `/clientes` | Listar clientes | ✅ |
| `GET` | `/clientes/{id}` | Buscar cliente por ID | ✅ |
| `POST` | `/veiculos` | Criar veículo | ✅ |
| `GET` | `/veiculos` | Listar veículos | ✅ |
| `GET` | `/veiculos/{id}` | Buscar veículo por ID | ✅ |
| `POST` | `/cotacoes` | Criar cotação (vincula cliente + veículo) | ✅ |
| `GET` | `/cotacoes` | Listar cotações | ✅ |
| `PUT` | `/cotacoes/{id}` | Atualizar cotação | ✅ |

Todas as respostas seguem o formato `ApiResponse<T>`:

```json
{
  "code": 0,
  "message": "OK",
  "data": { ... }
}
```

| code | Significado |
|------|-------------|
| 0 | Sucesso |
| -1 | Erro genérico |
| 1 | Erro de parâmetro |
| 2 | Email duplicado |
| 3 | CPF duplicado |
| 4 | Não encontrado |
| 5 | Não autorizado |
| 6 | Erro de validação |

---

## Fluxo de uso

```
1. POST /auth/signup   → cadastra usuário
2. POST /auth/login    → retorna token JWT
3. Usar token no header Authorization: Bearer <token>
4. POST /veiculos      → criar veículo
5. POST /clientes      → criar cliente
6. POST /cotacoes      → criar cotação vinculando cliente + veículo
```

### Exemplo de login

```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"lucas","password":"lucas@email.com"}'
```

Resposta:
```json
{"code":0,"message":"OK","data":{"accessToken":"eyJhbGciOiJIUzI1NiJ9..."}}
```

---

## Como executar

```bash
# Com Maven instalado globalmente
mvn spring-boot:run
```

Ou abra o projeto no IntelliJ e execute a classe `SegurosApplication`.

### Acessos

| Recurso | URL |
|---------|-----|
| API | `http://localhost:8080` |
| Swagger UI | `http://localhost:8080/swagger-ui/` |
| Console H2 | `http://localhost:8080/h2-console` (JDBC: `jdbc:h2:mem:segurosdb`) |

---

## Arquitetura

```
com.cotacao.seguros
├── controller     → REST endpoints
├── service        → Regras de negócio
├── repository     → Acesso a dados (Spring Data JPA)
├── entity         → Entidades JPA
├── dto            → Request/Response DTOs
├── mapper         → MapStruct (entity ↔ DTO)
├── security       → JWT, filtro de autenticação, UserDetailsService
├── config         → Swagger, Security, CORS
├── exception      → Tratamento global de exceções + ApiResponse
└── enums          → Códigos de erro (ResponseEnum)
```

---

## Tecnologias

- Java 8
- Spring Boot 2.7.18 (Web, Validation, Data JPA, Security)
- Springfox 3.0.0 (Swagger)
- jjwt 0.11.x (JWT HS256)
- Lombok
- MapStruct
- H2 Database (in-memory)
- Flyway (migrations)

---

## Documentação adicional

- `anotation/ENDPOINTS.md` — descrição detalhada dos endpoints
- `anotation/TESTES_CURL.md` — exemplos de comandos curl com respostas
