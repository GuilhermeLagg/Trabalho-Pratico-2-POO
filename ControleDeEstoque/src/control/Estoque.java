package control;

import entities.Comum;
import entities.Importado;
import entities.Produto;
import entities.Usado;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Estoque {
    private static List<Produto> produtos = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void cadastrarProduto(){

        System.out.print("Quantos produtos deseja cadastrar? ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.printf("Dados do produto #%d: %n", (i+1));

            System.out.print("Comum, importado ou usado? (c/i/u): ");
            char resp = sc.next().toLowerCase().charAt(0);
            sc.nextLine();

            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("Preço: ");
            double preco = sc.nextDouble();
            System.out.print("Quantidade inicial: ");
            int qtdeIni = sc.nextInt();

            if (resp == 'i'){
                System.out.print("Taxa de importação: R$");
                double taxa = sc.nextDouble();
                produtos.add(new Importado(nome, preco, qtdeIni, taxa));
            }
            if (resp == 'u'){
                sc.nextLine();
                System.out.print("Data de fabricação: ");
                String data = sc.nextLine();
                //para podermos inserir uma data no formato desejado, precisamos primeiro ler como String e dps transformar em LocalDate
                LocalDate dataFormatada = LocalDate.parse(data, fmt);
                produtos.add(new Usado(nome, preco, qtdeIni, dataFormatada));
            }
            if (resp == 'c'){
                produtos.add(new Comum(nome, preco, qtdeIni));
            }
            sc.nextLine();
        }
    }

    public static void comprarProduto(){
        System.out.println("Qual produto deseja comprar?");
        for (Produto p : produtos){
            System.out.println(p.getNome());
        }
        String x = sc.nextLine();

        for(Produto p : produtos){
            if(p.getNome().equals(x)){
                System.out.println("Quantas unidades deseja comprar?");
                int qtde = sc.nextInt();
                sc.nextLine();
                p.setQuantidade(p.getQuantidade() + qtde);
            }else{
                System.out.println("Produto não encontrado.");
            }
        }

    }

    public static void venderProduto(){
        System.out.println("Qual produto deseja vender?");
        for (Produto p : produtos){
            System.out.println(p.getNome());
        }
        String x = sc.nextLine();

        for (Produto p : produtos){
            if (p.getNome().equals(x)){
                System.out.println("Quantas unidades você deseja comprar?");
                int qtde = sc.nextInt();
                sc.nextLine();

                if(p.getQuantidade() <  qtde){
                    System.out.println("Quantidade indisponível em estoque.");
                }else if(p.getQuantidade() > qtde){
                    System.out.println("Produto vendido.");
                    p.setQuantidade(p.getQuantidade() - qtde);
                }else{
                    System.out.println("Produto removido do estoque.");
                    produtos.remove(p);
                    break;
                }
            }
        }
    }

    public static void imprimeProduto(){
        System.out.println();
        if (Estoque.estoqueVazio()) {
            System.out.println("O estoque está vazio! Adicione itens para poder visualizar.");
        } else {
            System.out.println("PRODUTOS EM ESTOQUE");
            for (Produto p : produtos){
                System.out.println(p);
                System.out.println();
            }
        }
    }

    public static void pesquisarProduto(){
        System.out.println("Qual produto deseja pesquisar?");
        String prod = sc.nextLine();

        for (Produto  p : produtos){
            if (p.getNome().equals(prod)){

            } else{
                System.out.println("Nenhum produto encontrado.");
            }
        }
    }

    public static boolean estoqueVazio(){
        if (produtos.isEmpty()){
            return true;
        }
        return false;
    }
}
