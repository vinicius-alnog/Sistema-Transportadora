# Sistema de Rastreamento de Entregas
Sistema em Java para gerenciamento de encomendas com rastreamento de status, controle de entregadores e cálculo de frete.

## Funcionalidades
* Cadastro de entregadores com nome e região de atuação
* Cadastro de encomendas com remetente, destinatário, endereço de destino e peso
* Código de encomenda gerado automaticamente (ENC-1, ENC-2...)
* Histórico completo de rastreio com data e hora de cada evento
* Transição de status automática e validada por ordem
* Devolução automática após 2 tentativas de entrega falhas
* Atribuição de entregador a encomenda com bloqueio de duplicidade
* Cálculo de frete por peso e distância
* Consulta de encomenda por código
* Listagem de encomendas por entregador

## Como Executar

### Pré-requisitos
* Java 17 ou superior instalado
* Terminal/CMD ou IDE Java (Eclipse, IntelliJ, VS Code)

### Exemplo de Uso
```
=== SISTEMA DE LOGÍSTICA ===
1 - Cadastrar entregador
2 - Cadastrar encomenda
3 - Adicionar evento de rastreio
4 - Atribuir entregador a encomenda
5 - Calcular frete
6 - Buscar encomenda por código
7 - Exibir encomendas por entregador
0 - Sair
Escolha uma opção: 2

Digite o remetente: João
Digite o destinatário: Maria
Digite o endereço do destinatário: Rua dos Pinheiros, 456
Digite a cidade do destinatário: Londrina
Digite o CEP do destinatário: 86000-000
Digite o peso da encomenda (kg): 3.5

Encomenda cadastrada com sucesso!
Encomenda: ENC-1
De: João
Para: Maria | Rua dos Pinheiros, 456 - Londrina (86000-000)
Peso: 3.5 kg
Status atual: COLETADO
```

## Fluxo de Status
```
COLETADO → EM_TRANSITO → SAIU_PARA_ENTREGA → ENTREGUE
                                          ↘ TENTATIVA_FALHA (x2) → EM_DEVOLUCAO
```
Qualquer tentativa de pular uma etapa lança uma exceção com mensagem de erro clara.

## Cálculo de Frete
```
Frete = (peso × R$ 3,50) + (distância em km × R$ 0,80)
```

## Estrutura do Projeto
```
src/
└── com/
    └── logistica/
        ├── model/
        │   ├── Endereco.java
        │   ├── StatusEncomenda.java
        │   ├── EventoRastreio.java
        │   ├── Encomenda.java
        │   └── Entregador.java
        ├── service/
        │   ├── Transportadora.java
        │   └── SistemaLogistica.java
        ├── exception/
        │   ├── TransicaoStatusInvalidaException.java
        │   └── EntregadorNaoEncontradoException.java
        └── App.java
```

## Objetivos do Projeto
Projeto desenvolvido como parte dos estudos de:
* Lógica de Programação
* Java Intermediário
* Programação Orientada a Objetos
* Estruturas de Dados

## Conceitos Aplicados
* **Encapsulamento:** Atributos privados com getters e setters
* **Herança e Polimorfismo:** Hierarquia de classes do sistema
* **Enumerações:** Controle de status com lógica de transição
* **Exceções customizadas:** Tratamento de erros específicos do negócio
* **Coleções:** Gerenciamento de listas com ArrayList
* **Membros estáticos:** Geração automática de códigos únicos
* **LocalDateTime:** Registro de data e hora dos eventos de rastreio
* **StringBuilder:** Formatação de saídas no toString das entidades

## Autor
Desenvolvido por Vinicius Antonio Lisboa Nogueira
* GitHub: **@vinicius-alnog**
* LinkedIn: **Vinicius Nogueira**