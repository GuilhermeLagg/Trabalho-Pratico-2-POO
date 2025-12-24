package model;

public class ItemCarrinho {
    private Produto produto;
    private int qtde;

    public ItemCarrinho(Produto produto, int qtde) {
        this.produto = produto;
        this.qtde = qtde;
    }

    public Produto getProduto(){
        return produto;
    }

    public void adicionarProduto(int quantidade){
        this.qtde += quantidade;
    }
}
