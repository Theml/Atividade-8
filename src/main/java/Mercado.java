package src.main.java;
import java.util.ArrayList;
import java.util.List;

// Classe que representa um mercado
public class Mercado {
    private int id;
    private String nome;
    private String endereco;
    private List<Produto> produtos;
    private final NotificacaoService notificacaoService;

    public Mercado(int id, String nome, String endereco, NotificacaoService notificacaoService) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.produtos = new ArrayList<>();
        this.notificacaoService = notificacaoService;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void removerProduto(Produto produto) {
        produtos.remove(produto);
    }

    public void processarPedido(PedidoMercado pedido) {
        notificacaoService.notificar("Sistema", "Mercado " + nome + " está processando o pedido #" + pedido.getId());

        pedido.setState(PedidoEmAndamento.getInstance());
        pedido.processarPedido();

        notificacaoService.notificar("Sistema", "Produtos separados e embalados. Pedido está saindo para entrega...");
        pedido.setState(PedidoEntregue.getInstance());
        pedido.processarPedido();
    }
    
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public List<Produto> getProdutos() {
        return new ArrayList<>(produtos);
    }
}