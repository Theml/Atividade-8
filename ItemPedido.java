public class ItemPedido {
    private Produto produto;
    private int quantidade;
    
    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }
    
    public double calcularSubtotal() {
        return produto.getValor() * quantidade;
    }
    
    public Produto getProduto() {
        return produto;
    }
    
    public int getQuantidade() {
        return quantidade;
    }
    
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
