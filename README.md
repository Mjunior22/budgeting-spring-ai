# Budgeting — API de Orçamento com IA

API que usa IA para processar comandos de texto e voz relacionados a transações financeiras pessoais.

## O que o projeto faz

- Recebe comandos em texto ou áudio (ex: "gastei 50 reais no mercado")
- Transcreve áudio em texto usando o Whisper (OpenAI)
- Usa IA (Anthropic Claude) para entender a intenção do comando
- Executa Tool Calling para registrar ou consultar transações reais no banco de dados
- Expõe endpoints REST convencionais para consultar, listar e remover transações

## Tecnologias usadas

- Java 21
- Spring Boot 4
- Spring AI 2.0 (Anthropic + OpenAI)
- Spring Data JPA + H2 (banco em memória)
- Gradle

## Como executar

1. Defina as variáveis de ambiente `ANTHROPIC_API_KEY` e `OPENAI_API_KEY`
2. Configure o `application.properties`:
```properties
   spring.ai.anthropic.api-key=${ANTHROPIC_API_KEY}
   spring.ai.openai.api-key=${OPENAI_API_KEY}
   spring.datasource.url=jdbc:h2:mem:budgeting
   spring.jpa.hibernate.ddl-auto=update
```
3. Rode `./gradlew bootRun` (ou execute a classe `Main.java` pela IDE)

## Endpoints

| Método | Rota | Descrição |
|---|---|---|
| POST | /comandos | Envia um comando em texto para a IA processar |
| POST | /comandos/audio | Envia um arquivo de áudio para transcrição + processamento |
| GET | /transacoes | Lista todas as transações |
| GET | /transacoes/{id} | Busca uma transação por id |
| DELETE | /transacoes/{id} | Remove uma transação |

## Melhorias implementadas

- **Endpoints REST dedicados** para consultar/remover transações sem depender da IA
- **System prompt refinado** para a IA responder em português, evitar alucinação de valores, e pedir esclarecimento quando o comando é ambíguo

## Como testar

Rode `./gradlew test` — inclui testes unitários (`TransacaoRepositoryTest`, `TransacaoToolsTest`) que não dependem de API externa, e testes de integração (`*IT`) que exigem `ANTHROPIC_API_KEY`/`OPENAI_API_KEY` configuradas com crédito disponível.

> Nota: a transcrição de áudio foi implementada e compila corretamente, mas não foi validada com chamada real à API por limitação de crédito na conta OpenAI no momento da entrega.

## O que aprendi

- Configurar Spring AI 2.0 com múltiplos provedores (Anthropic e OpenAI) no mesmo projeto
- Usar Tool Calling para conectar IA a lógica de negócio real
- Diferenças entre testes de integração (`@SpringBootTest`) e testes de slice (`@DataJpaTest`)
- Lidar com mudanças de API entre versões (Spring Boot 3→4, Spring AI 1.x→2.0)
