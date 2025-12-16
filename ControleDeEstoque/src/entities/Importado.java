package entities;

public class Importado extends Produto{
    private double taxaImportacao;

    public Importado(){
        super();
    }

    public Importado(String nome, double preco, int quantidade, double taxaImportacao){
        super(nome, preco, quantidade);
        this.taxaImportacao = taxaImportacao;
    }

    public double getTaxaImportacao() {
        return taxaImportacao;
    }

    @Override
    public double precoTotal(){
        return (preco * quantidade) + taxaImportacao;
    }

    @Override
    public String toString(){
        return "Nome: "
                + nome
                + "\nPreço: R$"
                + String.format("%.2f%n", preco)
                + "Quantidade: "
                + quantidade
                + "\nTaxa de Importação: R$"
                + String.format("%.2f", taxaImportacao)
                + "\nPreço total: R$"
                + String.format("%.2f", precoTotal());
    }
}
