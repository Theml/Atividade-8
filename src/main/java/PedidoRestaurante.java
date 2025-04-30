import java.time.LocalDateTime;

public class PedidoRestaurante extends Pedido {
    private String observacoesCozinha;
    private boolean precisaPreparo;

    public PedidoRestaurante(int id, Cliente cliente) {
        super(id, cliente);
        this.precisaPreparo = true;
    }

    public void preparar() {
        if (precisaPreparo) {
            System.out.println("Preparando comida...");
            if (observacoesCozinha != null) {
                System.out.println("Observações: " + observacoesCozinha);
            }
        }
        this.precisaPreparo = false;
    }

    public void setObservacoesCozinha(String observacoes) {
        this.observacoesCozinha = observacoes;
    }

    public void setPrecisaPreparo(boolean precisa) {
        this.precisaPreparo = precisa;
    }
}