package src.main.java;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

// Classe base para todos os tipos de pedidos - Utilizada pelo Factory Method
public abstract class Pedido extends Observable {
    private int id;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private PedidoState state;
    private double valorTotal;

    protected Pedido(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.state = PedidoEmAndamento.getInstance();
        this.valorTotal = 0.0;
        this.addObserver(cliente);
    }

    public void adicionarItem(Produto produto, int quantidade) {
        itens.add(new ItemPedido(produto, quantidade));
        calcularValor();
    }

    public void removerItem(ItemPedido item) {
        itens.remove(item);
        calcularValor();
    }

    public void calcularValor() {
        this.valorTotal = itens.stream()
                .mapToDouble(ItemPedido::calcularSubtotal)
                .sum();
    }

    public void setState(PedidoState newState) {
        this.state = newState;
        setChanged();
        notifyObservers(String.format("Pedido #%d agora está: %s", id, state.getNomeState()));
    }

    public void processarPedido() {
        state.processarPedido(this);
    }

    public abstract void preparar();

    public int getId() {
        return id;
    }

    public PedidoState getState() {
        return state;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }
}