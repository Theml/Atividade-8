public class PedidoFarmaciaFactory implements PedidoFactory {
    
    public Pedido criarPedido(int id, Cliente cliente) {
        return new PedidoFarmacia(id, cliente);
    }
}