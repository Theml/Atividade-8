package src.main.java;
import java.util.Optional;
// Factory Pedido feito de Restaurante
public class PedidoComida extends Pedido {
    private Restaurante restaurante;
    private String observacoes;
    private final NotificacaoService notificacaoService;

    public PedidoComida(int id, Cliente cliente, NotificacaoService notificacaoService) {
        super(id, cliente);
        this.notificacaoService = notificacaoService;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public void preparar() {
        notificacaoService.notificar("Sistema", "Preparando pedido de comida do restaurante: " +
                (restaurante != null ? restaurante.getNome() : "Não definido"));
        
        Optional.ofNullable(observacoes)
               .filter(obs -> !obs.isEmpty())
               .ifPresent(obs -> notificacaoService.notificar("Sistema", "Observações: " + obs));
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public String getObservacoes() {
        return observacoes;
    }
}