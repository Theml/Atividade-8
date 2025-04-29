# Atividade-8

## 1. Padrão Singleton

SistemaIFood.java: Implementa o padrão Singleton garantindo que exista apenas uma instância do sistema central

Construtor privado
Variável estática instance
Método getInstance() para acessar a instância única

## 2. Padrão Factory Method

PedidoFactory.java: Implementa o padrão Factory Method para criar diferentes tipos de pedidos

criarPedido() retorna diferentes tipos de pedidos baseados no parâmetro tipo
Classes concretas: PedidoComida e PedidoMercado

## 3. Padrão State

EstadoPedido.java: Interface que define o comportamento comum para todos os estados
Estados concretos que implementam diferentes comportamentos:

PedidoEmAndamento.java: Estado quando o pedido está sendo processado
PedidoEntregue.java: Estado quando o pedido foi entregue com sucesso
PedidoCancelado.java: Estado quando o pedido foi cancelado

Cada estado implementa sua própria lógica no método processarPedido()
A classe Pedido contém uma referência ao estado atual e delega o comportamento

## 4. Padrão Observer

O padrão Observer é implementado usando a API Java padrão (java.util.Observable e java.util.Observer)
Cliente.java: Implementa a interface Observer para receber notificações
Pedido.java: Estende Observable para notificar os observadores sobre mudanças de estado
Quando um pedido muda de estado, ele notifica automaticamente os clientes registrados

Outras Classes Importantes

Restaurante.java e Mercado.java: Representam os estabelecimentos do sistema
Produto.java e ItemPedido.java: Representam os produtos e itens dos pedidos

## Fluxo de funcionamento

Quando o sistema é executado, você verá como os pedidos são criados, processados, e como os clientes recebem notificações automáticas sobre as mudanças de estado. A estrutura do código segue os princípios de design patterns e orientação a objetos, proporcionando um sistema flexível e extensível.
Ao executar o programa, o fluxo será:

Criar clientes, restaurantes e mercados
Cadastrar produtos nos estabelecimentos
Criar pedidos usando o padrão Factory Method
Processar pedidos, demonstrando as transições de estado (padrão State)
Mostrar como os clientes recebem notificações (padrão Observer)
Exibir um resumo dos pedidos de cada cliente

## Diagrama
```mermaid
classDiagram
    %% Módulo Usuário
    class Usuario {
        -String nome
        -String email
        -String endereco
        +realizarPedido()
        +visualizarPedidos()
        +cancelarPedido()
    }
    
    class Cliente {
        -String nome
        -String email
        -String endereco
        -String telefone
        +realizarPedido()
        +visualizarPedidos()
        +cancelarPedido()
        +receberNotificacao(String mensagem)
    }
    
    %% Módulo Restaurante
    class Restaurante {
        -String nome
        -String endereco
        -List~Produto~ cardapio
        -List~Observer~ observers
        +adicionarProduto()
        +removerProduto()
        +atualizarStatus()
        +addObserver(Observer o)
        +removeObserver(Observer o)
        +notifyObservers()
    }
    
    %% Módulo Singleton
    class SistemaIFood {
        -static SistemaIFood instance
        -List~Usuario~ usuarios
        -List~Restaurante~ restaurantes
        -List~Pedido~ pedidos
        -SistemaIFood()
        +static getInstance() SistemaIFood
        +cadastrarUsuario()
        +cadastrarRestaurante()
        +buscarRestaurante()
        +processarPagamento()
    }
    
    %% Módulo Factory Method
    class PedidoFactory {
        +criarPedido(TipoPedido tipo) Pedido
    }
    
    class Pedido {
        -int id
        -Usuario cliente
        -Restaurante restaurante
        -List~ItemPedido~ itens
        -EstadoPedido estado
        -double valorTotal
        +adicionarItem()
        +removerItem()
        +calcularValor()
        +setEstado(EstadoPedido estado)
        +processarPedido()
    }
    
    class PedidoNormal {
        +processarPedido()
    }
    
    class PedidoExpresso {
        +processarPedido()
    }
    
    class PedidoAgendado {
        -Date horarioEntrega
        +processarPedido()
    }
    
    %% Módulo State
    class EstadoPedido {
        <<interface>>
        +processarEstado(Pedido pedido)
        +getNomeEstado() String
    }
    
    class EstadoAguardandoConfirmacao {
        +processarEstado(Pedido pedido)
        +getNomeEstado() String
    }
    
    class EstadoEmPreparacao {
        +processarEstado(Pedido pedido)
        +getNomeEstado() String
    }
    
    class EstadoEmEntrega {
        +processarEstado(Pedido pedido)
        +getNomeEstado() String
    }
    
    class EstadoEntregue {
        +processarEstado(Pedido pedido)
        +getNomeEstado() String
    }
    
    class EstadoCancelado {
        +processarEstado(Pedido pedido)
        +getNomeEstado() String
    }
    
    %% Módulo Observer
    class Observer {
        <<interface>>
        +update(String mensagem)
    }
    
    class ClienteObserver {
        -Cliente cliente
        +update(String mensagem)
    }
    
    %% Produto e Item Pedido
    class Produto {
        -int id
        -String nome
        -String descricao
        -double valor
    }
    
    class ItemPedido {
        -Produto produto
        -int quantidade
        +calcularSubtotal()
    }
    
    %% Relacionamentos
    SistemaIFood --o Usuario : contém
    SistemaIFood --o Cliente : contém
    SistemaIFood --o Restaurante : contém
    SistemaIFood --o Pedido : gerencia
    
    Usuario <|-- Cliente : extends
    
    Pedido --o ItemPedido : contém
    ItemPedido --o Produto : referência
    
    Pedido "1" *-- "1" EstadoPedido : possui estado atual
    EstadoPedido <|.. EstadoAguardandoConfirmacao : implementa
    EstadoPedido <|.. EstadoEmPreparacao : implementa
    EstadoPedido <|.. EstadoEmEntrega : implementa
    EstadoPedido <|.. EstadoEntregue : implementa
    EstadoPedido <|.. EstadoCancelado : implementa
    
    Pedido <|-- PedidoNormal : extends
    Pedido <|-- PedidoExpresso : extends
    Pedido <|-- PedidoAgendado : extends
    
    PedidoFactory ..> Pedido : cria
    
    Observer <|.. ClienteObserver : implementa
    Restaurante "1" o-- "*" Observer : notifica
    Cliente "1" -- "*" Pedido : realiza
    Cliente ..|> Observer : implementa
    Restaurante "1" -- "*" Pedido : atende
```
