package model;

public abstract class Produto {
    private static int contador = 1000;
    protected int id;
    protected String nome;
    protected double preco;
    protected int quantidade;

    public Produto(){};

    public Produto(String nome,double preco, int quantidade){
        contador++;
        this.id = contador;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public int getId(){ return id;}

    public String getNome(){
        return nome;
    }

    public double getPreco(){
        return preco;
    }

    public int getQuantidade(){
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public abstract double precoTotal();

}
