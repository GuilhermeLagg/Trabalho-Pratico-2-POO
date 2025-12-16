package entities;

public abstract class Produto {
    protected String nome;
    protected double preco;
    protected int quantidade;

    public Produto(){};

    public Produto(String nome,double preco, int quantidade){
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNome(){
        return nome;
    }

    public double getPreco(){
        return preco;
    }

    public int getQuantidade(){
        return quantidade;
    }

    public abstract double precoTotal();
}
