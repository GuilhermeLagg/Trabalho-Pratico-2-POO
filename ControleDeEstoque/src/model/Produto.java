package model;

public abstract class Produto {
    private static int contador = 1000;
    protected int id;
    protected String nome;
    protected double preco;
    protected int quantidadeProduto;

    public Produto(){};

    public Produto(String nome,double preco, int quantidade){
        contador++;
        this.id = contador;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeProduto = quantidade;
    }

    public int getId(){ return id;}

    public String getNome(){
        return nome;
    }

    public double getPreco(){
        return preco;
    }

    public int getQuantidadeProduto(){
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public abstract double precoTotal();

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Preço: " + preco + " | Quantidade: " + quantidadeProduto;
    }


}
