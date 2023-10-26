# Teste Técnico AUDSAT

Teste técnico para o processo seletivo para desenvolvedor backend da AUDSAT desenvolvido em Java 17 e Spring Boot 3.1.5.

## Arquitetura

![diagrama](docs/diagrama.png)

## Deploy

O projeto está dockerizado e pode ser executado com o comando abaixo:

```bash
docker-compose up --build
```

## Swagger

Após o deploy, a documentação da API pode ser acessada através do link abaixo:

```
http://localhost:8100/insurance/swagger-ui/index.html
```

Também é possível consultar a documentação da API com especificação OPENAPI 3.0 no arquivo `./docs/api-docs.yml`.

### Endpoints

#### POST /insurance/budget

Endpoint para criação de um orçamento.

- Request Payload

```
{
  "customerId": 1,
  "carId": 1,
  "isActive": true
}

```

- Response Payload

```json
{
  "success": true,
  "message": null,
  "data": {
    "id": 1
  }
}
```

----------------------------------------------------------------------------

#### GET /insurance/budget/:insuranceId

Endpoint para consulta de um orçamento.

- Response Payload

```json
{
  "success": true,
  "message": null,
  "data": {
    "insuranceId": 1,
    "creationDate": "2023-10-26T13:34:23.442454Z",
    "updatedDate": null,
    "isActive": true,
    "budget": 11831.90,
    "car": {
      "id": 1,
      "model": "Civic Sedan SPORT 2.0 Flex 16V Aut.4p",
      "fipeValue": 118319.0,
      "year": "2021",
      "manufacturer": "Honda"
    },
    "customer": {
      "id": 1,
      "name": "Puba Beariopen Blute",
      "birthdate": "1980-10-20",
      "document": "19019464640"
    }
  }
}
```

----------------------------------------------------------------------------

#### PUT /insurance/budget/:insuranceId

Endpoint para atualização de um orçamento.

- Request Payload

```
{
  "customerId": 1,
  "carId": 1,
  "isActive": true
}

```

- Response Payload

```json
{
  "success": true,
  "message": null,
  "data": null
}
```

----------------------------------------------------------------------------

#### DELETE /insurance/budget/:insuranceId

Endpoint para exclusão de um orçamento.

- Response Payload

```json
{
  "success": true,
  "message": null,
  "data": null
}
```

