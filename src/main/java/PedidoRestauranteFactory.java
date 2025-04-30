public class PedidoRestauranteFactory implements PedidoFactory {

    public Pedido criarPedido(int id, Cliente cliente) {
        PedidoRestaurante pedido = new PedidoRestaurante(id, cliente);
        System.out.println("Novo pedido de restaurante criado: #" + id);
        return pedido;
    }
}