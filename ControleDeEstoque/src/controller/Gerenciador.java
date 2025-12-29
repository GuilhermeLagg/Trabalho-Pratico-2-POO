package controller;

import model.Usuario;

import java.util.Scanner;

public class Gerenciador {
    private static final Scanner sc = new Scanner(System.in);

    public void iniciarPrograma() {
        Usuario.cadastrarUsuario();
        int opcao;
        do {
            opcao = menu();
            switch (opcao) {
                case 1:
                    Estoque.imprimeCarrinho();
                    break;
                case 2:
                    Estoque.visualizarEstoque();
                    break;
                case 3:
                    Estoque.adicionarAoCarrinho();
                    break;
                case 4:
                    Estoque.removerDoCarrinho();
                    break;
                case 5:
                    Estoque.pesquisarProduto();
                    break;
                case 6:
                    //Não coloquei ainda pra esse metodo finalizar o programa ou zerar o carrinho
                    Estoque.finalizarCompra();
                    break;
                case 7:
                    System.out.println("Saindo do programa...");
            }
        } while (opcao != 7);

    }

    public int menu() {
        System.out.println("\nBem vindo ao estoque da loja XPTO \n");
        System.out.println("Informe a operação que deseja realizar:");
        System.out.println("""
                1 - Visualizar Carrinho
                2 - Visualizar Estoque
                3 - Adicionar produto ao Carrinho
                4 - Retirar produto do Carrinho
                5 - Pesquisar produto
                6 - Finalizar Compra
                7 - Sair
                """);
        return sc.nextInt();
    }


}
