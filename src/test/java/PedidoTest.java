import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

class PedidoTest {
    private Cliente cliente;
    private LocalDateTime dataAgendamento;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("João Silva");
        dataAgendamento = LocalDateTime.now().plus(1, ChronoUnit.HOURS);
    }

    // Testes genéricos para a classe Pedido
    @Test
    void testNovoPedidoSemEstadoInicial() {
        PedidoRestaurante pedido = new PedidoRestaurante(1, cliente);
        assertNull(pedido.getEstado(), "Pedido novo não deve ter estado definido até ser agendado");
    }

    @Test
    void testAgendamentoPedido() {
        PedidoRestaurante pedido = new PedidoRestaurante(1, cliente);
        pedido.agendar(dataAgendamento);

        assertTrue(pedido.getEstado() instanceof EstadoAgendado);
        assertEquals(dataAgendamento, pedido.getDataAgendamento());
    }

    @Test
    void testAgendamentoDataPassado() {
        PedidoRestaurante pedido = new PedidoRestaurante(1, cliente);
        assertThrows(IllegalArgumentException.class, () ->
                pedido.agendar(LocalDateTime.now().minusHours(1)));
    }

    // Testes específicos para PedidoRestaurante
    @Test
    void testPreparoPedidoRestaurante() {
        PedidoRestaurante pedido = new PedidoRestaurante(1, cliente);
        pedido.setObservacoesCozinha("Sem cebola");

        assertDoesNotThrow(() -> pedido.preparar());
    }

    @Test
    void testPreparoComObservacoes() {
        PedidoRestaurante pedido = new PedidoRestaurante(1, cliente);
        pedido.setObservacoesCozinha("Sem lactose");
        pedido.preparar(); // Verifique a saída manualmente ou com Mock
    }

    // Testes específicos para PedidoMercado
    @Test
    void testPreparoPedidoMercado() {
        PedidoMercado pedido = new PedidoMercado(2, cliente);
        pedido.setPrecisaEmbalagemEspecial(true);

        assertDoesNotThrow(() -> pedido.preparar());
    }

    // Testes específicos para PedidoFarmacia
    @Test
    void testPreparoPedidoFarmacia() {
        PedidoFarmacia pedido = new PedidoFarmacia(3, cliente);
        pedido.setReceitaMedica(true);

        assertDoesNotThrow(() -> {
            pedido.preparar();
            pedido.verificarValidadeMedicamentos();
        });
    }

    // Testes de transição de estados
    @Test
    void testTransicaoParaEntregue() {
        PedidoRestaurante pedido = new PedidoRestaurante(1, cliente);
        pedido.agendar(dataAgendamento);
        pedido.marcarComoEntregue();

        assertTrue(pedido.getEstado() instanceof EstadoEntregue);
        assertNotNull(pedido.getDataEntrega());
    }

    @Test
    void testTransicaoParaCancelado() {
        PedidoMercado pedido = new PedidoMercado(2, cliente);
        pedido.agendar(dataAgendamento);
        pedido.cancelar();

        assertTrue(pedido.getEstado() instanceof EstadoCancelado);
    }

    @Test
    void testCancelamentoSemAgendamento() {
        PedidoFarmacia pedido = new PedidoFarmacia(3, cliente);
        assertThrows(IllegalStateException.class, () -> pedido.cancelar());
    }

    // Teste de notificação (usando Mock)
    @Test
    void testNotificacaoMudancaEstado() {
        Cliente clienteMock = mock(Cliente.class);
        PedidoRestaurante pedido = new PedidoRestaurante(1, clienteMock);

        pedido.agendar(dataAgendamento);
        verify(clienteMock).update(eq(pedido), contains("Agendado"));

        pedido.marcarComoEntregue();
        verify(clienteMock).update(eq(pedido), contains("Entregue"));
    }
}