// Estado de Pedido cancelado
public class PedidoCancelado {

    private PedidoCancelado() {};

    private static PedidoCancelado instance = new PedidoCancelado();
    public static PedidoCancelado getInstance(){
        return instance;
    }

}
