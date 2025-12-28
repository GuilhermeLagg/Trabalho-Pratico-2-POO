package controller;

import model.Comum;
import model.Importado;
import model.ItemCarrinho;
import model.Produto;

import javax.print.attribute.standard.OrientationRequested;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Estoque {
    //Vamos criar uma lista de produtos em estoque (produtos esses que não vao mudar. São os produtos disponíveis pra compra).
    private static List<Produto> produtos = new ArrayList<>();

    //Essa outra lista é o nosso carrinho. Vamos add os produtos ao carrinho pra que possamos comprá-los.
    private static List<ItemCarrinho> carrinho = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void visualizarEstoque(){

        //aqui estão listados os produtos disponiveis no estoque (ate o momento, só hardcode mesmo, a n ser que tenha outra forma melhor de colocar eles)
        produtos.add(new Comum("Notebook Acer", 2200.00, 10));
        produtos.add(new Importado("Iphone 14 pro max",8000.00, 20, 500.00));
        produtos.add(new Comum("Placa de Vídeo RTX 550", 1200.00, 1));
        produtos.add(new Importado("Apple Watch", 1673.00, 15, 1000.00));
        produtos.add(new Comum("Samsumg Galaxy Buds", 289.00, 5));
        produtos.add(new Importado("Apple Ipad 11 A16", 2949.00, 30, 430.00));
        produtos.add(new Comum("Projetor Smart Full HD 4k", 194.35, 10));
        produtos.add(new Importado("Smart Ring HALO", 447.90, 5, 500.00));
        produtos.add(new Comum("Amazon Echo Dot", 459.00, 20));
        produtos.add(new Importado("Carregador Magnético Sem Fio", 1423.99, 10, 1000.00));

        //mostrando os produtos em estoque
        System.out.println("Produtos em Estoque");
        for (Produto p : produtos) {
            System.out.println("**************************");
            System.out.println(p);
            System.out.println("**************************");
            System.out.println();
        }
    }

    //metodo para adicionar os produtos no carrinho através do id
    public static void adicionarAoCarrinho(){
        System.out.println("Qual produto deseja adicionar? Informe o id:");
        for (Produto p : produtos){
            System.out.println("ID: " +p.getId() + " | Produto: " + p.getNome());
        }
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Qual a quantidade? ");
        int qtde = sc.nextInt();
        sc.nextLine();

        //verificando se o produto já está no carrinho, se estiver, só adiciona a quantidade
        for(ItemCarrinho item : carrinho){
            if (item.getProduto().getId() == id){
                item.adicionarProduto(qtde);
                return;
            }
        }
        //lógica de caso não tenha o produto no carrinho
        for(Produto p : produtos){
            if(p.getId() == id){
                carrinho.add(new ItemCarrinho(p, qtde));
                break;
            }else{
                System.out.println("Produto não encontrado");
            }
        }

    }

    public static void removerDoCarrinho(){
        System.out.println("Qual produto deseja remover? Informe o id:");
        for (ItemCarrinho item : carrinho){
            System.out.println("ID: " + item.getProduto().getId() + " | Produto: " + item.getProduto().getNome());
        }
        int id = sc.nextInt();
        sc.nextLine();
        for(ItemCarrinho item : carrinho){
            if(item.getProduto().getId() == id){
                carrinho.remove(item);
                System.out.println("Produto removido com sucesso");
                break;
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

    public static void imprimeCarrinho(){
        System.out.println();
        if (Estoque.carrinhoVazio()) {
            System.out.println("O carrinho está vazio! Adicione itens ao carrinho para poder visualizar.");
        } else {
            System.out.println("PRODUTOS NO CARRINHO");
            for (ItemCarrinho item : carrinho){
                System.out.println("Produto: " + item.getProduto().getNome() + " | Quantidade: "+item.getProduto().getQuantidade() + " | ID: " + item.getProduto().getId());
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

    public static boolean carrinhoVazio(){
        if (carrinho.isEmpty()){
            return true;
        }
        return false;
    }
}
