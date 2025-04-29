import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Observer {
    private final String nome;
    private final String email;
    private final String endereco;
    private final String telefone;
    private final List<Pedido> pedidos;
    private final NotificacaoService notificacaoService;

    public Cliente(String nome, String email, String endereco, String telefone, NotificacaoService notificacaoService) {
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.pedidos = new ArrayList<>();
        this.notificacaoService = notificacaoService;
    }

    public void update(Observable observable, Object arg) {
        Optional.of(observable)
                .filter(obs -> obs instanceof Pedido)
                .map(obs -> (String) arg)
                .ifPresent(this::receberNotificacao);
    }

    public void receberNotificacao(String mensagem) {
        notificacaoService.notificar(nome, mensagem);
    }

    public Pedido realizarPedido(PedidoFactory factory, String tipoPedido) {
        Pedido pedido = factory.criarPedido(tipoPedido, this);
        pedidos.add(pedido);
        return pedido;
    }

    public void visualizarPedidos() {
        pedidos.forEach(pedido -> 
            notificacaoService.notificar(nome, formatarPedido(pedido)));
    }

    private String formatarPedido(Pedido pedido) {
        return String.format("Pedido #%d - Estado: %s - Valor: R$%.2f", 
            pedido.getId(), 
            pedido.getState().getNomeState(), 
            pedido.getValorTotal());
    }

    public void cancelarPedido(Pedido pedido) {
        pedido.setState(PedidoCancelado.getInstance());
        pedido.processarPedido();
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }
}