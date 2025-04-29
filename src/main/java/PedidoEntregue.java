// Estado de Pedido Entregue - Implementação do padrão State
public class PedidoEntregue implements PedidoState {

    private static PedidoEntregue instance = new PedidoEntregue();
    private static final NotificacaoService notificacaoService = new MockNotificacaoService();

    private PedidoEntregue() {}

    public static PedidoEntregue getInstance() {
        return instance;
    }

    @Override
    public void processarPedido(Pedido pedido) {
        notificacaoService.notificar("Sistema", "Pedido #" + pedido.getId() + " foi entregue com sucesso.");
    }

    @Override
    public String getNomeState() {
        return "Entregue";
    }
}