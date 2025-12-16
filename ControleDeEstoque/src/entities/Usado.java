package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Usado extends Produto{
    private LocalDate dataFabricacao;

    public Usado(){
        super();
    }

    public Usado(String nome, double preco, int quantidade, LocalDate dataFabricacao){
        super(nome, preco, quantidade);
        this.dataFabricacao = dataFabricacao;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    @Override
    public double precoTotal(){
        return preco * quantidade;
    }

    @Override
    public String toString(){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return "Nome: "
                + nome
                + "\nPreço: R$"
                + String.format("%.2f%n", preco)
                + "Quantidade: "
                + quantidade
                + "\nData de Fabricação: "
                + dataFabricacao.format(fmt)
                + "\nPreço total: R$"
                + String.format("%.2f", precoTotal());
    }
}
