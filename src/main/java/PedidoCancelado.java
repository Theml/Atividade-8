// Estado de Pedido cancelado - Implementação do padrão State
public class PedidoCancelado implements PedidoState {

    private static PedidoCancelado instance = new PedidoCancelado();
    private static final NotificacaoService notificacaoService = new MockNotificacaoService();

    private PedidoCancelado() {}

    public static PedidoCancelado getInstance() {
        return instance;
    }

    @Override
    public void processarPedido(Pedido pedido) {
        notificacaoService.notificar("Sistema", "Pedido #" + pedido.getId() + " foi cancelado.");
    }

    @Override
    public String getNomeState() {
        return "Cancelado";
    }
}