import java.time.LocalDateTime;

public class PedidoFarmacia extends Pedido{
    private boolean receitaMedica;

    public PedidoFarmacia(int id, Cliente cliente){
        super(id, cliente);
    }

    public void preparar() {
        System.out.println("Separando medicamentos...");
        if (receitaMedica) {
            System.out.println("Validando receita médica");
        }
    }

    public void setReceitaMedica(boolean temReceita) {
        this.receitaMedica = temReceita;
    }

    public void verificarValidadeMedicamentos() {
        System.out.println("Verificando prazos de validade");
    }
}
