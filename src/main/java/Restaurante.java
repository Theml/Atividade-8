package src.main.java;
import java.util.ArrayList;
import java.util.List;

public class Restaurante {
    private final int id;
    private final String nome;
    private final String endereco;
    private final List<Produto> cardapio;
    private final NotificacaoService notificacaoService;

    public Restaurante(int id, String nome, String endereco, NotificacaoService notificacaoService) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.notificacaoService = notificacaoService;
        this.cardapio = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        cardapio.add(produto);
        notificacaoService.notificar("Sistema", "Produto " + produto.getNome() + " adicionado ao cardápio");
    }

    public void removerProduto(Produto produto) {
        cardapio.remove(produto);
        notificacaoService.notificar("Sistema", "Produto " + produto.getNome() + " removido do cardápio");
    }

    public void processarPedido(PedidoComida pedido) {
        notificacaoService.notificar("Sistema", "Restaurante " + nome + " está processando o pedido #" + pedido.getId());

        pedido.setState(PedidoEmAndamento.getInstance());
        pedido.processarPedido();

        notificacaoService.notificar("Sistema", "Pedido #" + pedido.getId() + " está saindo para entrega");
        pedido.setState(PedidoEntregue.getInstance());
        pedido.processarPedido();
    }

    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public List<Produto> getCardapio() { return new ArrayList<>(cardapio); }
}