package src.main.java;
public interface PedidoState {
    void processarPedido(Pedido pedido);
    String getNomeState();
}