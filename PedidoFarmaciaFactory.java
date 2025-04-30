public class PedidoFarmaciaFactory implements PedidoFactory {
    
    public Pedido criarPedido(int id, Cliente cliente) {
        return new PedidoFarmacia(id, cliente);
    }

    public Pedido criarPedido(int id, Cliente cliente, dataAgendamento) {
        return new PedidoFarmacia(id, cliente, dataAgendamento);
    }
}