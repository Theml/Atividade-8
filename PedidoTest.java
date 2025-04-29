import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PedidoTest {
    private Pedido pedido;
    private Cliente clienteMock;
    private NotificacaoService notificacaoServiceMock;
    
    @BeforeEach
    public void setUp() {
        notificacaoServiceMock = mock(NotificacaoService.class);
        clienteMock = new Cliente("João", "joao@test.com", "Rua Teste", "123456789");
        pedido = new PedidoComida(1, clienteMock, notificacaoServiceMock);
    }
    
    @Test
    public void testAdicionarItem() {
        Produto produto = new Produto(1, "Produto Teste", 10.0);
        pedido.adicionarItem(produto, 2);
        
        assertEquals(1, pedido.getItens().size());
        assertEquals(20.0, pedido.getValorTotal());
    }
    
    @Test
    public void testRemoverItem() {
        Produto produto = new Produto(1, "Produto Teste", 10.0);
        ItemPedido item = new ItemPedido(produto, 2);
        pedido.adicionarItem(produto, 2);
        pedido.removerItem(item);
        
        assertEquals(0, pedido.getItens().size());
        assertEquals(0.0, pedido.getValorTotal());
    }
    
    @Test
    public void testStateTransition() {
        pedido.setState(PedidoEmAndamento.getInstance());
        assertEquals("Em Andamento", pedido.getState().getNomeState());
        
        pedido.setState(PedidoEntregue.getInstance());
        assertEquals("Entregue", pedido.getState().getNomeState());
    }
    
    @Test
    public void testObserverNotification() {
        pedido.setState(PedidoCancelado.getInstance());
        // Verifica se a notificação foi enviada ao cliente
        verify(notificacaoServiceMock, atLeastOnce()).notificar(anyString(), anyString());
    }

    @Test
    public void testPedidoFactoryCriacaoTipos() {
        Pedido comida = pedidoFactory.criarPedido("comida", cliente);
        assertTrue(comida instanceof PedidoComida);
        
        Pedido mercado = pedidoFactory.criarPedido("mercado", cliente);
        assertTrue(mercado instanceof PedidoMercado);
        
        assertThrows(IllegalArgumentException.class, () -> 
            pedidoFactory.criarPedido("invalido", cliente));
    }

    @Test
    public void testPedidoMercadoSubstituicao() {
        PedidoMercado pedido = (PedidoMercado) cliente.realizarPedido(sistema.getPedidoFactory(), "mercado");
        pedido.setPrecisaSubstituicao(true);
        pedido.preparar();
        
        // Verificar se a notificação de substituição foi enviada
        MockNotificacaoService mock = (MockNotificacaoService) notificacaoService;
        assertTrue(mock.getNotificacoes().stream()
            .anyMatch(msg -> msg.contains("substituição")));
    }

    @Test
    public void testRestauranteProcessamentoPedido() {
        PedidoComida pedido = (PedidoComida) cliente.realizarPedido(sistema.getPedidoFactory(), "comida");
        pedido.setRestaurante(restaurante);
        pedido.adicionarItem(produtoRestaurante, 1);
        
        restaurante.processarPedido(pedido);
        
        assertEquals("Entregue", pedido.getState().getNomeState());
    }
}