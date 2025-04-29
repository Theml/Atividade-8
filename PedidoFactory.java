import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PedidoFactory {
    private static int nextId = 1;
    private final Map<String, PedidoCreator> creators;
    private final NotificacaoService notificacaoService;

    public PedidoFactory(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
        this.creators = new HashMap<>();
        registerCreators();
    }

    private void registerCreators() {
        creators.put("comida", (id, cliente) -> new PedidoComida(id, cliente, notificacaoService));
        creators.put("mercado", (id, cliente) -> new PedidoMercado(id, cliente, notificacaoService));
    }

    public Pedido criarPedido(String tipo, Cliente cliente) {
        return Optional.ofNullable(creators.get(tipo.toLowerCase()))
                .map(creator -> creator.criar(nextId++, cliente))
                .orElseThrow(() -> new IllegalArgumentException("Tipo de pedido não suportado: " + tipo));
    }
}

@FunctionalInterface
interface PedidoCreator {
    Pedido criar(int id, Cliente cliente);
}