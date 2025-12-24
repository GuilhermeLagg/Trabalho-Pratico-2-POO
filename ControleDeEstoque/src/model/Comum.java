package model;

public class Comum extends Produto{
    public Comum (){
        super();
    }

    public Comum (String nome, double preco, int quantidade){
        super(nome, preco, quantidade);
    }

    @Override
    public double precoTotal(){
        return preco * quantidade;
    }

    @Override
    public String toString(){
        return  "Id: "
                + id
                + "\nNome: "
                + nome
                + "\nPreço: R$"
                + String.format("%.2f%n", preco)
                + "Quantidade: "
                + quantidade
                + "\nPreço total: R$"
                + String.format("%.2f", precoTotal());
    }
}
