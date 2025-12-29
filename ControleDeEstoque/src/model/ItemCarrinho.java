package model;

public class ItemCarrinho {
    private Produto produto;
    private int quantidadeCarrinho;

    public ItemCarrinho(){};

    public ItemCarrinho(Produto produto, int qtde) {
        this.produto = produto;
        this.quantidadeCarrinho = qtde;
    }

    public Produto getProduto(){
        return produto;
    }

    public int getQuantidadeCarrinho(){
        return quantidadeCarrinho;
    }

    public void adicionarProduto(int quantidade){
        this.quantidadeCarrinho += quantidade;
    }

    //faz o subtotal de todos os produtos do carrinho
    //precisamos consertar a logica, porque ele nao esta acessando o preco do produto (polimorfismo, cada tipo de produto tem uma regra de calculo diferente)
    public double produtoValorCarrinho(){
        return quantidadeCarrinho * produto.getPreco();
    }
}
