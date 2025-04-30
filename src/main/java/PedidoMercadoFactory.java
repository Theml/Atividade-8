public class PedidoMercadoFactory implements PedidoFactory {

    public Pedido criarPedido(int id, Cliente cliente) {
        return new PedidoMercado(id, cliente);
    }
}