public class PedidoEmAndamento implements PedidoState {
    private static PedidoEmAndamento instance = new PedidoEmAndamento();
    private static final NotificacaoService notificacaoService = new MockNotificacaoService();

    private PedidoEmAndamento() {}

    public static PedidoState getInstance() {
        return instance;
    }

    @Override
    public void processarPedido(Pedido pedido) {
        notificacaoService.notificar("Sistema", "Processando pedido #" + pedido.getId() + " em andamento.");
        pedido.preparar();
    }

    @Override
    public String getNomeState() {
        return "Em Andamento";
    }
}