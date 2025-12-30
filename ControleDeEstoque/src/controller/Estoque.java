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

        if (produtos.isEmpty()){
            System.out.println("Estoque vazio ou arquivo não encontrado");
            return;
        }

        //mostrando os produtos em estoque
        System.out.println("Produtos em Estoque");
        for (Produto p : produtos) {
            System.out.println("**************************");
            System.out.println(p);
            System.out.println("**************************");
        }
    }

    //metodo para adicionar os produtos no carrinho através do id
    public static void adicionarAoCarrinho() {
        System.out.println("Qual produto deseja adicionar? Informe o id:");
        System.out.println("========================================================================");
        for (Produto p : produtos){
            System.out.println("ID: " +p.getId() + " | Produto: " + p.getNome());
        }
        System.out.println("========================================================================");
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
            System.out.println("ID: " + item.getProduto().getId() + " | Produto: " + item.getProduto().getNome() + " | Quantidade: " + item.getQuantidadeCarrinho());
        }
        System.out.print("--> ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Quantas unidades? ");
        int qtde = sc.nextInt();

        //Lógica para remoção dos objetos do carrinho, primeiro if retirando o produto por completo, o segundo certa quantidade e por fim não removendo por não possuir tal quantidade desejada
        for(ItemCarrinho item : carrinho){
            if(item.getProduto().getId() == id){
                if(item.getQuantidadeCarrinho()==qtde){
                    carrinho.remove(item);
                    totalCarrinho -= item.produtoValorCarrinho();
                    System.out.println("Produto removido com sucesso");
                }else if(item.getQuantidadeCarrinho()>qtde){
                    item.setQuantidadeCarrinho(item.getQuantidadeCarrinho()-qtde);
                    totalCarrinho -= item.getProduto().getPreco()*qtde;
                    System.out.println("Quantidade removida com sucesso");
                }else{
                    System.out.println("Você não possui essa quantidade no carrinho.");
                }
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
        System.out.println("Informe o ID do produto desejado:");
        int id = sc.nextInt();
        boolean encontrado = false;
        for (Produto  p : produtos){
            if (p.getId() == id){
                System.out.println("==================================================");
                System.out.println("       Informações do produto desejado:");
                System.out.println("Produto: " + p.getNome() + " | Quantidade: " + p.getQuantidadeProduto() + " | ID: " + p.getId());
                System.out.println("==================================================");
                encontrado = true;
                break;
            }
        }
        if (!encontrado ){
            System.out.println("=========================================");
            System.out.println("Produto com ID " + id + " não localizado.");
            System.out.println("=========================================");
        }
    }

    public static void finalizarCompra(){

        System.out.println("Finalizando Compra...\n");
        if (carrinhoVazio()) {
            System.out.println("Seu carrinho está vazio!");
            return;
        }

        System.out.println("Valor total do seu carrinho: " + totalCarrinho);
        Usuario.confirmarUsuario();

        //agora nós temos que TIRAR do estoque o que o usuario comprou, pra que ele possa ficar atualizado
        for (ItemCarrinho item : carrinho){
            Produto p = item.getProduto();
            //vamos SUBTRAIR o que temos no estoque pelo que compramos e que está no nosso carrinho
            //ai usamos um setter pra poder atualizar a qtde no estoque
            p.setQuantidadeProduto(p.getQuantidadeProduto() - item.getQuantidadeCarrinho());
        }

       //a atualização foi feita só no estoque "fisico", mas precisamos fazer no "virtual". Ou seja, atualizar no arquivo tbm
       GerenciadorArquivos.salvarEstoque(produtos); //aq ele vai REESCREVER o arquivo, mas agora com os produtos atualizados!

        //agora vamos gerar nosso recibo ao final da compra
        GerenciadorArquivos.gerarRelatorioCompra(carrinho, totalCarrinho);

        //vamos limpar o carrinho pra prox compra
        carrinho.clear(); //esse clear() limpa a lista - "reseta"
        totalCarrinho = 0; //precisamos tbm resetar o valor que estava no carrinho

        System.out.println("\nCompra finalizada!");
    }

    public static boolean carrinhoVazio(){
        if (carrinho.isEmpty()){
            return true;
        }
        return false;
    }
}
