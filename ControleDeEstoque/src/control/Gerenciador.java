package control;

import java.util.Scanner;

public class Gerenciador {

    private static Scanner sc = new Scanner(System.in);

    public void iniciarPrograma() {
        int opcao;
        do {
            opcao = menu();
            switch (opcao) {
                case 1:
                    Estoque.imprimeProduto();
                    break;
                case 2:
                    Estoque.cadastrarProduto();
                    break;
                case 3:
                    Estoque.comprarProduto();
                    break;
                case 4:
                    Estoque.venderProduto();
                    break;
                case 5:
                    Estoque.pesquisarProduto();
                    break;
            }
        } while (opcao != 6);

    }

    public int menu() {
        System.out.println("\nBem vindo ao estoque da loja XPTO!\n");
        System.out.println("Informe a operação que deseja realizar:");
        System.out.println("""
                1 - Visualizar Estoque
                2 - Cadastrar produto
                3 - Comprar produto
                4 - Vender produto
                5 - Pesquisar produto
                6 - Sair
                """);
        return sc.nextInt();
    }


}
