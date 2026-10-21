# Economics API

API REST desenvolvida com Java e Spring Boot para consulta e disponibilização de indicadores econômicos, utilizando dados do Banco Central do Brasil (BCB).

O projeto tem como objetivo praticar o desenvolvimento de APIs REST, integração com serviços externos, organização em camadas e tratamento global de exceções.

## Objetivos

- Consumir dados econômicos por meio da API do Banco Central.
- Desenvolver endpoints REST para consulta de indicadores.
- Utilizar DTOs para organizar a entrada e a saída de dados.
- Separar responsabilidades entre Controller, Service e Client.
- Implementar tratamento centralizado de exceções.
- Aplicar boas práticas de desenvolvimento com Java e Spring Boot.

## Tecnologias

- **Java**
- **Spring Boot**
- **Spring Web**
- **RestClient**
- **Jackson**
- **Maven**
- **API de Séries Temporais do Banco Central do Brasil (SGS)**

## Arquitetura do projeto

```text
economics/
├── controller/
│   └── EconomicIndicatorController.java
├── service/
│   └── EconomicIndicatorService.java
├── client/
│   ├── BcbApiClient.java
│   └── dto/
│       └── BcbSeriesDTO.java
├── model/
│   ├── EconomicIndicator.java
│   └── EconomicIndicatorType.java
├── dto/
│   └── IndicatorResponseDTO.java
└── exception/
    ├── GlobalExceptionHandler.java
    ├── ErrorResponse.java
    └── InvalidDateRangeException.java
```

## Indicadores econômicos

A estrutura inicial foi planejada para trabalhar com indicadores como:

- **IPCA:** índice de inflação.
- **Selic:** taxa básica de juros.
- **USD/BRL:** cotação do dólar em reais.

A implementação inicial utiliza o IPCA como exemplo de consulta. Os demais indicadores dependem da configuração de suas respectivas séries e endpoints.

## Endpoint inicial

### Consultar IPCA

```http
GET /api/indicators/ipca
```

Parâmetros:

| Parâmetro | Descrição | Formato |
|---|---|---|
| `startDate` | Data inicial da consulta | `yyyy-MM-dd` |
| `endDate` | Data final da consulta | `yyyy-MM-dd` |

Exemplo:

```http
GET /api/indicators/ipca?startDate=2026-01-01&endDate=2026-09-30
```

Exemplo ilustrativo de resposta:

```json
[
  {
    "name": "IPCA",
    "value": 0.48,
    "unit": "%",
    "referenceDate": "2026-09-01",
    "source": "Banco Central do Brasil"
  }
]
```

*Os valores apresentados são fictícios e servem apenas para ilustrar o formato da resposta.*

## Tratamento de exceções

O projeto prevê um tratamento centralizado de erros por meio de `@RestControllerAdvice`, permitindo padronizar as respostas de erro da API.

Exemplos de situações tratadas:

- Intervalo de datas inválido.
- Falhas na comunicação com a API externa.
- Erros inesperados durante a execução.

As respostas de erro incluem informações como status HTTP, mensagem, data e hora e caminho da requisição.

## Como executar

### Pré-requisitos

- JDK compatível com a versão Java configurada no projeto.
- Maven.
- Conexão com a internet para consultar a API do Banco Central.

### Passos

1. Clone o repositório:

   ```bash
   git clone <URL_DO_REPOSITORIO>
   ```

2. Acesse a pasta do projeto:

   ```bash
   cd economics
   ```

3. Compile o projeto:

   ```bash
   mvn clean package
   ```

4. Execute a aplicação:

   ```bash
   mvn spring-boot:run
   ```

5. Consulte o endpoint de indicadores usando o Postman, Insomnia ou outro cliente HTTP.

> Os comandos pressupõem que o projeto esteja configurado com o Maven Wrapper ou com o Maven instalado e que as dependências necessárias estejam declaradas no `pom.xml`.

## Fonte dos dados

Os dados são obtidos por meio da API de séries temporais do Banco Central do Brasil.

- [Banco Central do Brasil — Dados Abertos](https://dadosabertos.bcb.gov.br/)

## Status

Projeto em desenvolvimento, com foco em aprendizado prático de Java, Spring Boot e integração com APIs externas.

## Licença

A licença ainda não foi definida.
