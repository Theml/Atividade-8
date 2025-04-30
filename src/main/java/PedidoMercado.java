import java.time.LocalDateTime;

public class PedidoMercado extends Pedido {
    private boolean precisaEmbalagemEspecial;

    public PedidoMercado(int id, Cliente cliente) {
        super(id, cliente);
    }

    public void preparar() {
        System.out.println("Separando itens do mercado...");
        if (precisaEmbalagemEspecial) {
            System.out.println("Usando embalagem especial para produtos frágeis");
        }
    }

    public void setPrecisaEmbalagemEspecial(boolean precisa) {
        this.precisaEmbalagemEspecial = precisa;
    }
}