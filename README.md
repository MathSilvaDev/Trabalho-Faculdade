# API de pedidos

Projeto feito em Spring Boot com Java, JPA e MySQL.

A API gerencia:

- Clientes
- Produtos
- Pedidos

## Entidades

**Cliente**: id, nome e data de criacao.

**Produto**: id, nome, preco e status de estoque.

**Pedido**: id, cliente, produto, quantidade e data de criacao.

## Endpoints

### Clientes

- `GET /api/clientes`
- `GET /api/clientes/{id}`
- `POST /api/clientes`
- `PATCH /api/clientes/{id}`
- `DELETE /api/clientes/{id}`

### Produtos

- `GET /api/produtos`
- `GET /api/produtos/{id}`
- `POST /api/produtos`
- `PATCH /api/produtos/{id}`
- `PATCH /api/produtos/{id}/toggle-in-stock`
- `DELETE /api/produtos/{id}`

### Pedidos

- `GET /api/pedidos`
- `GET /api/pedidos/{id}`
- `POST /api/pedidos`
- `DELETE /api/pedidos/{id}`

Os `GET` de listagem aceitam paginacao com `pageNumber` e `pageSize`.

## Docker

O Docker esta em:

```text
docker/docker-compose.yaml
```

Para subir o MySQL:

```bash
docker compose -f docker/docker-compose.yaml up -d
```

Depois, rode a aplicacao com:

```bash
./mvnw spring-boot:run
```

A API roda em `http://localhost:8080`.

Esse foi um projeto feito para um trabalho da faculdade e apos isso vou apagar esse repositorio
