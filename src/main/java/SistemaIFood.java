import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SistemaIFood {
    private static SistemaIFood instance;
    private final List<Cliente> clientes;
    private final List<Restaurante> restaurantes;
    private final List<Mercado> mercados;
    private final List<Pedido> pedidos;
    private final PedidoFactory pedidoFactory;
    private final NotificacaoService notificacaoService;

    private SistemaIFood(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
        this.clientes = new ArrayList<>();
        this.restaurantes = new ArrayList<>();
        this.mercados = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.pedidoFactory = new PedidoFactory(notificacaoService);
    }

    public static synchronized SistemaIFood getInstance(NotificacaoService notificacaoService) {
        if (instance == null) {
            instance = new SistemaIFood(notificacaoService);
        }
        return instance;
    }

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
        notificacaoService.notificar("Sistema", "Cliente " + cliente.getNome() + " cadastrado com sucesso");
    }

    public Optional<Cliente> buscarCliente(String email) {
        return clientes.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public void cadastrarRestaurante(Restaurante restaurante) {
        restaurantes.add(restaurante);
        notificacaoService.notificar("Sistema", "Restaurante " + restaurante.getNome() + " cadastrado com sucesso");
    }

    public void cadastrarMercado(Mercado mercado) {
        mercados.add(mercado);
        notificacaoService.notificar("Sistema", "Mercado " + mercado.getNome() + " cadastrado com sucesso");
    }

    public Pedido criarPedido(String tipo, Cliente cliente) {
        Pedido pedido = pedidoFactory.criarPedido(tipo, cliente);
        pedidos.add(pedido);
        notificacaoService.notificar("Sistema", "Novo pedido #" + pedido.getId() + " criado");
        return pedido;
    }

    public List<Pedido> listarPedidosCliente(Cliente cliente) {
        return pedidos.stream()
                .filter(p -> p.getCliente().equals(cliente))
                .collect(Collectors.toList());
    }

    public List<Cliente> getClientes() {
        return new ArrayList<>(clientes);
    }

    public List<Restaurante> getRestaurantes() {
        return new ArrayList<>(restaurantes);
    }

    public List<Mercado> getMercados() {
        return new ArrayList<>(mercados);
    }

    public List<Pedido> getPedidos() {
        return new ArrayList<>(pedidos);
    }

    public PedidoFactory getPedidoFactory() {
        return pedidoFactory;
    }
    
    public static void resetInstance() {
        instance = null;
    }
}
