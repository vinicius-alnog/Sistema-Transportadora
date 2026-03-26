# Sistema de Rastreamento de Entregas

Um sistema de gerenciamento de encomendas feito em Java, onde é possível cadastrar entregadores, registrar encomendas, acompanhar o status de cada entrega em tempo real e calcular fretes. Tudo via terminal com um menu interativo.

---

## Como funciona

O sistema simula o fluxo de uma transportadora. Ao cadastrar uma encomenda, ela recebe um código automático (ENC-1, ENC-2...) e entra no sistema com o status inicial de **COLETADO**. A partir daí, o status avança conforme a entrega progride:

```
COLETADO → EM_TRANSITO → SAIU_PARA_ENTREGA → ENTREGUE
                                          ↘ TENTATIVA_FALHA
```

Se o entregador tentar entregar e não conseguir duas vezes seguidas, o sistema detecta automaticamente e inicia o processo de devolução, mudando o status para **EM_DEVOLUCAO** sem precisar de intervenção manual.

Qualquer tentativa de pular uma etapa do fluxo — como tentar marcar uma encomenda como entregue sem ela ter saído para entrega — é bloqueada com uma mensagem de erro clara.

---

## Funcionalidades

- Cadastro de entregadores com nome e região de atuação
- Cadastro de encomendas com remetente, destinatário, endereço de destino e peso
- Histórico completo de rastreio com data e hora de cada evento
- Atribuição de entregador a encomenda, com bloqueio para evitar atribuição duplicada
- Cálculo de frete baseado no peso e na distância informada
- Consulta de encomenda por código
- Listagem de todas as encomendas atribuídas a um entregador

---

## Cálculo de frete

O frete é calculado com a seguinte fórmula:

```
Frete = (peso × R$ 3,50) + (distância em km × R$ 0,80)
```

---

## Estrutura do projeto

O projeto foi organizado em camadas para separar responsabilidades:

```
src/
└── com/
    └── logistica/
        ├── model/            → entidades do sistema
        │   ├── Endereco.java
        │   ├── StatusEncomenda.java
        │   ├── EventoRastreio.java
        │   ├── Encomenda.java
        │   └── Entregador.java
        ├── service/          → regras de negócio e menu
        │   ├── Transportadora.java
        │   └── SistemaLogistica.java
        ├── exception/        → exceções personalizadas
        │   ├── TransicaoStatusInvalidaException.java
        │   └── EntregadorNaoEncontradoException.java
        └── App.java
```

- **model** — classes que representam os dados do sistema, sem lógica de negócio
- **service** — onde ficam as regras de negócio e o controle do menu interativo
- **exception** — exceções criadas para situações específicas do sistema, como transição de status inválida ou entregador não encontrado

---

## Tecnologias

- Java 17
- Orientação a Objetos
- Enumerações com lógica de transição de status
- Exceções customizadas
- ArrayList e List para gerenciamento de coleções
- LocalDateTime para registro de data e hora dos eventos

---

## Como rodar

1. Clone o repositório:
```bash
git clone https://github.com/viniciusnogueira/projeto-logistica.git
```

2. Abra na sua IDE preferida (foi desenvolvido no IntelliJ IDEA)

3. Rode a classe `App.java`

4. Interaja pelo menu no terminal

---

## Autor

Vinicius Nogueira