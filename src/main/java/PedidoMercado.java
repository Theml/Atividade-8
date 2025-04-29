package src.main.java;
import java.util.Optional;
public class PedidoMercado extends Pedido {
    private Mercado mercado;
    private boolean precisaSubstituicao;
    private final NotificacaoService notificacaoService;

    public PedidoMercado(int id, Cliente cliente, NotificacaoService notificacaoService) {
        super(id, cliente);
        this.notificacaoService = notificacaoService;
    }

    public void setMercado(Mercado mercado) {
        this.mercado = mercado;
    }

    public void setPrecisaSubstituicao(boolean precisaSubstituicao) {
        this.precisaSubstituicao = precisaSubstituicao;
    }

    @Override
    public void preparar() {
        notificacaoService.notificar("Sistema", "Preparando pedido de mercado: " + 
            Optional.ofNullable(mercado).map(Mercado::getNome).orElse("Não definido"));
        
        if (precisaSubstituicao) {
            notificacaoService.notificar("Sistema", "Este pedido permite substituição de itens em falta.");
        }
    }

    public Mercado getMercado() {
        return mercado;
    }

    public boolean isPrecisaSubstituicao() {
        return precisaSubstituicao;
    }
}