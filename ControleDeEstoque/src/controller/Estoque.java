package controller;

import model.*;
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

    //Variável responsável por armazenar o valor total dos produtos no carrinho (N sei se seria a melhor forma de fazer isso)
    private static double totalCarrinho;

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
    public static void adicionarAoCarrinho() {
        System.out.println("Qual produto deseja adicionar? Informe o id:");
        for (Produto p : produtos){
            System.out.println("ID: " +p.getId() + " | Produto: " + p.getNome());
        }
        System.out.print("--> ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Qual a quantidade? ");
        int qtde = sc.nextInt();
        sc.nextLine();

        //verificando se o produto já está no carrinho, se estiver, só adiciona a quantidade
        for(ItemCarrinho item : carrinho){
            //precisamos verificar aqui se o estoque eh suficiente pra poder add no carrinho
            //aqui vamos usar a excecao personalizada EstoqueInsuficienteException
            if (item.getProduto().getId() == id){
                item.adicionarProduto(qtde);
                totalCarrinho +=item.getProduto().getPreco()*qtde;
                return;
            }
        }
        //lógica de caso não tenha o produto no carrinho
        for(Produto p : produtos){
            if(p.getId() == id){
                carrinho.add(new ItemCarrinho(p, qtde));
                System.out.println(qtde + " unidades de " + p.getNome() + " adicionado ao carrinho!");
                totalCarrinho +=p.getPreco()*qtde;
                break;
            }
        }


    }

    public static void removerDoCarrinho(){
        System.out.println("Qual produto deseja remover do carrinho? Informe o id:");
        for (ItemCarrinho item : carrinho){
            System.out.println("ID: " + item.getProduto().getId() + " | Produto: " + item.getProduto().getNome());
        }
        System.out.print("--> ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Quantas unidades? ");
        int qtde = sc.nextInt();
        //se usarmos so o carrinho.remove(item) nos vamos excluir o objeto inteiro do carrinho
        //mas precisamos excluir somente a quantidade desejada pelo usuario.
        //caso o usuario queira remover tudo do item, ai sim podemos usar carrinho.remove(item).
        for(ItemCarrinho item : carrinho){
            if(item.getProduto().getId() == id){
                carrinho.remove(item);
                totalCarrinho -= item.produtoValorCarrinho();
                System.out.println("Produto removido com sucesso");
                break;
            }
        }
    }


    public static void imprimeCarrinho(){
        System.out.println();
        if (Estoque.carrinhoVazio()) {
            System.out.println("========================================================================");
            System.out.println("O carrinho está vazio! Adicione itens ao carrinho para poder visualizar.");
            System.out.println("========================================================================");
        } else {
            System.out.println("============= PRODUTOS NO CARRINHO =============");
            for (ItemCarrinho item : carrinho){
                System.out.println("Produto: " + item.getProduto().getNome() + " | Quantidade: " + item.getQuantidadeCarrinho() + " | ID: " + item.getProduto().getId());
                System.out.println();
            }
            System.out.printf("Subtotal no carrinho: R$%.2f%n", totalCarrinho);
            System.out.println("================================================");

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

    public static void finalizarCompra(){
        System.out.println("Finalizando Compra...\n");
        if (Estoque.carrinhoVazio()) {
            System.out.println("Seu carrinho está vazio, deseja finalizar mesmo assim? (sim/nao)");
            String resposta = sc.nextLine();
            if (resposta.equalsIgnoreCase("sim")){
                System.out.println("Obrigado por comprar na loja XPTO!");
            }
        }else{
            System.out.println("Valor total do seu carrinho: R$" + totalCarrinho);
            Usuario.confirmarUsuario();
        }
    }

    public static boolean carrinhoVazio(){
        if (carrinho.isEmpty()){
            return true;
        }
        return false;
    }
}
