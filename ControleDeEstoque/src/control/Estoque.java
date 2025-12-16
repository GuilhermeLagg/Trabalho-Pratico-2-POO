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
                LocalDate dataFormatada = LocalDate.parse(data, fmt);
                produtos.add(new Usado(nome, preco, qtdeIni, dataFormatada));
            }
            if (resp == 'c'){
                produtos.add(new Comum(nome, preco, qtdeIni));
            }
        }
    }

    public static void comprarProduto(){

    }

    public static void venderProduto(String x){
        for (Produto p : produtos){
            if (p.getNome().equals(x)){
                produtos.remove(p);
            }
        }
    }

    public static void imprimeProduto(){
        System.out.println();
        System.out.println("PRODUTOS EM ESTOQUE");
        for (Produto p : produtos){
            System.out.println(p);
            System.out.println();
        }
    }

    public static boolean estoqueVazio(){
        if (produtos.isEmpty()){
            return true;
        }
        return false;
    }
}
